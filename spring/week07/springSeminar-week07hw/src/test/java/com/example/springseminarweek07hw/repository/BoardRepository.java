package com.example.springseminarweek07hw.repository;

import com.example.springseminarweek07hw.domain.Board;
import com.example.springseminarweek07hw.domain.Comment;
import com.example.springseminarweek07hw.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BoardRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @Autowired BoardRepository boardRepository;
    @Autowired CommentRepository commentRepository;
    @Autowired TestEntityManager tem;

    @Test
    @DisplayName("Board 저장 시 author(FK)가 필수이며 LAZY 로딩 동작")
    void save_board_with_author() {
        Member author = memberRepository.save(Member.builder().username("author1").displayName("작성자1").build());
        Board b = boardRepository.save(Board.builder().title("hello").content("world").author(author).build());

        tem.flush(); tem.clear();

        Board found = boardRepository.findById(b.getId()).orElseThrow();
        // 연관 엔티티 접근 시 프록시 초기화 (DataJpaTest는 트랜잭션 유지)
        assertThat(found.getAuthor().getUsername()).isEqualTo("author1");
    }

    @Test
    @DisplayName("Board 삭제 시 댓글이 함께 삭제된다(cascade + orphanRemoval)")
    void delete_board_cascade_comments() {
        Member author = memberRepository.save(Member.builder().username("a2").displayName("작성자2").build());
        Board b = boardRepository.save(Board.builder().title("t").content("c").author(author).build());

        // 댓글 2개
        Comment c1 = Comment.builder().content("c1").board(b).author(author).build();
        Comment c2 = Comment.builder().content("c2").board(b).author(author).build();
        // 편의 메서드로 양방향 연결
        b.addComment(c1);
        b.addComment(c2);
        commentRepository.save(c1);
        commentRepository.save(c2);

        tem.flush(); tem.clear();

        // when
        boardRepository.deleteById(b.getId());
        tem.flush(); tem.clear();

        // then
        assertThat(boardRepository.findAll()).isEmpty();
        assertThat(commentRepository.findAll()).isEmpty(); // 댓글도 함께 삭제됨
    }
}
