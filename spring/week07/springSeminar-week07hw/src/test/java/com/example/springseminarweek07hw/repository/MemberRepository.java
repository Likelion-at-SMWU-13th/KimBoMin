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
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @Autowired BoardRepository boardRepository;
    @Autowired CommentRepository commentRepository;
    @Autowired TestEntityManager tem;

    @Test
    @DisplayName("Member 저장/조회")
    void save_and_find_member() {
        Member m = Member.builder().username("bomin").displayName("보민").build();
        Member saved = memberRepository.save(m);

        tem.flush(); tem.clear();

        Member found = memberRepository.findById(saved.getId()).orElseThrow();
        assertThat(found.getUsername()).isEqualTo("bomin");
        assertThat(found.getDisplayName()).isEqualTo("보민");
    }

    @Test
    @DisplayName("Member ↔ Board/Comment 양방향 연관관계 확인(OneToMany는 역방향)")
    void relations_of_member() {
        // given
        Member m = memberRepository.save(Member.builder().username("author").displayName("작성자").build());
        Board b = boardRepository.save(Board.builder().title("title").content("content").author(m).build());
        Comment c = commentRepository.save(Comment.builder().content("hi").board(b).author(m).build());

        // when
        tem.flush(); tem.clear();

        Member found = memberRepository.findById(m.getId()).orElseThrow();

        // then (역방향 컬렉션 조회가 가능해야 함)
        assertThat(found.getBoards()).hasSize(1);
        assertThat(found.getComments()).hasSize(1);
        assertThat(found.getBoards().get(0).getAuthor().getId()).isEqualTo(found.getId());
        assertThat(found.getComments().get(0).getAuthor().getId()).isEqualTo(found.getId());
    }
}
