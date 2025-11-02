package com.likelion.banking.integration;

import com.likelion.banking.domain.Account;
import com.likelion.banking.exception.AccountNotFoundException;
import com.likelion.banking.repository.AccountRepository;
import com.likelion.banking.service.TransferService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

/**
 * TransferService의 통합 테스트
 * 
 * 단위 테스트와의 차이점:
 * - @SpringBootTest로 스프링 컨텍스트가 실행됨
 * - @Autowired로 실제 빈을 주입받음
 * - @MockBean으로 스프링 컨텍스트의 빈을 Mock으로 대체
 * 
 * 목표:
 * - 스프링 컨텍스트 내에서 테스트 수행
 * - @Transactional 등 스프링 기능이 정상 작동하는지 확인
 */
@SpringBootTest
class TransferServiceIntegrationTests {

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private TransferService transferService;

    /**
     * 실습 2-1: 스프링 통합 테스트
     * 
     * 단위 테스트와 동일한 시나리오지만,
     * 이번에는 스프링 컨텍스트가 실행된 상태에서 테스트합니다.
     */
    @Test
    @DisplayName("통합 테스트: 스프링 컨텍스트 내에서 계좌 이체가 수행된다")
    void transferMoneyIntegrationTest() {
        // TODO: Given - 테스트 데이터 준비
        // 힌트: 단위 테스트와 동일하게 작성하면 됩니다
        Account sender = new Account(1L, "John", new BigDecimal(1000));
        Account receiver = new Account(2L, "John", new BigDecimal(1000));

        given(accountRepository.findById(1L))
                .willReturn(Optional.of(sender));
        given(accountRepository.findById(2L))
                .willReturn(Optional.of(receiver));

        // TODO: When - 메서드 실행
        transferService.transferMoney(1L, 2L, new BigDecimal(100));

        // TODO: Then - 결과 검증
        verify(accountRepository).changeAmount(1L, new BigDecimal(900));
        verify(accountRepository).changeAmount(2L, new BigDecimal(1100));
    }

    /**
     * 실습 2-2: 단위 테스트와 실행 시간 비교 (선택)
     * 
     * 이 테스트는 작성하지 않아도 됩니다.
     * 대신 전체 테스트 실행 시간을 비교해보세요!
     */

    /**
     * 과제: 통합 테스트 예외 플로우 1
     * 발신인 계좌를 찾을 수 없는 경우
     * 
     * 시나리오:
     * - 발신인 계좌(ID: 999)는 존재하지 않음
     * 
     * 예상 결과:
     * - AccountNotFoundException이 발생해야 함
     * - @Transactional에 의해 롤백되어 changeAmount()는 호출되지 않아야 함
     */
    @Test
    @DisplayName("통합 테스트 예외 플로우: 발신인 계좌를 찾을 수 없으면 예외가 발생하고 롤백된다")
    void transferMoneyIntegrationTest_SenderAccountNotFound() {
        // Given - 발신인 계좌가 존재하지 않는 상황 설정
        given(accountRepository.findById(999L))
                .willReturn(Optional.empty());
        
        // When & Then - 예외 발생 확인
        assertThrows(AccountNotFoundException.class, () -> {
            transferService.transferMoney(999L, 2L, new BigDecimal(100));
        });
        
        // Then - @Transactional 롤백 검증: changeAmount()가 호출되지 않았는지 확인
        verify(accountRepository, never())
                .changeAmount(anyLong(), any(BigDecimal.class));
    }

    /**
     * 과제: 통합 테스트 예외 플로우 2
     * 수취인 계좌를 찾을 수 없는 경우 (통합 테스트)
     * 
     * 시나리오:
     * - 발신인 계좌(ID: 1)는 존재함
     * - 수취인 계좌(ID: 999)는 존재하지 않음
     * 
     * 예상 결과:
     * - AccountNotFoundException이 발생해야 함
     * - @Transactional에 의해 롤백되어 changeAmount()는 호출되지 않아야 함
     * 
     * 통합 테스트의 특징:
     * - 스프링 컨텍스트가 실행된 상태에서 테스트
     * - @Transactional 어노테이션이 실제로 작동하는지 확인
     */
    @Test
    @DisplayName("통합 테스트 예외 플로우: 수취인 계좌를 찾을 수 없으면 예외가 발생하고 롤백된다")
    void transferMoneyIntegrationTest_ReceiverAccountNotFound() {
        // Given - 발신인 계좌는 존재하지만 수취인 계좌는 존재하지 않는 상황 설정
        Account sender = new Account(1L, "John", new BigDecimal(1000));
        
        given(accountRepository.findById(1L))
                .willReturn(Optional.of(sender));
        given(accountRepository.findById(999L))
                .willReturn(Optional.empty());
        
        // When & Then - 예외 발생 확인
        AccountNotFoundException exception = assertThrows(
                AccountNotFoundException.class,
                () -> transferService.transferMoney(1L, 999L, new BigDecimal(100))
        );
        
        // Then - 예외 메시지 확인
        assertTrue(exception.getMessage().contains("Receiver"));
        
        // Then - @Transactional 롤백 검증: changeAmount()가 호출되지 않았는지 확인
        // 예외가 발생했으므로 트랜잭션이 롤백되어 데이터베이스 변경사항이 되돌려져야 함
        verify(accountRepository, never())
                .changeAmount(anyLong(), any(BigDecimal.class));
    }
}