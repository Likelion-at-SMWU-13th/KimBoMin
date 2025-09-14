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
class CommentRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @Autowired BoardRepository boardRepository;
    @Autowired CommentRepository commentRepository;
    @Autowired TestEntityManager tem;

    @Test
    @DisplayName("Comment 저장/조회 및 다대일 양쪽 FK 확인")
    void save_and_find_comment() {
        Member m = memberRepository.save(Member.builder().username("writer").displayName("작성자").build());
        Board b = boardRepository.save(Board.builder().title("T").content("C").author(m).build());

        Comment c = commentRepository.save(Comment.builder()
                .content("댓글 내용").board(b).author(m).build());

        tem.flush(); tem.clear();

        Comment found = commentRepository.findById(c.getId()).orElseThrow();
        assertThat(found.getContent()).isEqualTo("댓글 내용");
        assertThat(found.getBoard().getId()).isEqualTo(b.getId());
        assertThat(found.getAuthor().getId()).isEqualTo(m.getId());
    }
}
