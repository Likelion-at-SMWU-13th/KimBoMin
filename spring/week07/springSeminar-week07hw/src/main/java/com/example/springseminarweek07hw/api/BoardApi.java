package com.example.springseminarweek07hw.api;

import com.example.springseminarweek07hw.domain.Board;
import com.example.springseminarweek07hw.domain.Comment;
import com.example.springseminarweek07hw.domain.Member;
import com.example.springseminarweek07hw.dto.BoardDto;
import com.example.springseminarweek07hw.dto.CommentDto;
import com.example.springseminarweek07hw.dto.MemberDto;
import com.example.springseminarweek07hw.repository.BoardRepository;
import com.example.springseminarweek07hw.repository.CommentRepository;
import com.example.springseminarweek07hw.repository.MemberRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApi {

    private final MemberRepository memberRepo;
    private final BoardRepository boardRepo;
    private final CommentRepository commentRepo;

    // Member
    @PostMapping("/members")
    public ResponseEntity<MemberDto.SimpleRes> createMember(@Valid @RequestBody MemberDto.CreateReq req){
        Member m = Member.builder().username(req.username()).displayName(req.displayName()).build();
        memberRepo.save(m);
        return ResponseEntity.ok(new MemberDto.SimpleRes(m.getId(), m.getUsername(), m.getDisplayName()));
    }

    // Board
    @PostMapping("/boards")
    @Transactional
    public ResponseEntity<Long> createBoard(@Valid @RequestBody BoardDto.CreateReq req){
        Member author = memberRepo.findById(req.authorId())
                .orElseThrow(() -> new IllegalArgumentException("author not found"));
        Board b = Board.builder().title(req.title()).content(req.content()).author(author).build();
        boardRepo.save(b);
        return ResponseEntity.ok(b.getId());
    }

    @GetMapping("/boards/{id}")
    @Transactional
    public ResponseEntity<BoardDto.DetailRes> getBoard(@PathVariable Long id){
        Board b = boardRepo.findById(id).orElseThrow();
        var comments = b.getComments().stream()
                .map(c -> new BoardDto.CommentRes(
                        c.getId(), c.getContent(),
                        c.getAuthor().getId(), c.getAuthor().getUsername(),
                        c.getCreatedAt()))
                .collect(Collectors.toList());
        BoardDto.DetailRes res = new BoardDto.DetailRes(
                b.getId(), b.getTitle(), b.getContent(),
                b.getAuthor().getId(), b.getAuthor().getUsername(),
                b.getCreatedAt(), b.getUpdatedAt(), comments
        );
        return ResponseEntity.ok(res);
    }

    // Comment
    @PostMapping("/comments")
    @Transactional
    public ResponseEntity<Long> createComment(@Valid @RequestBody CommentDto.CreateReq req){
        Board board = boardRepo.findById(req.boardId())
                .orElseThrow(() -> new IllegalArgumentException("board not found"));
        Member author = memberRepo.findById(req.authorId())
                .orElseThrow(() -> new IllegalArgumentException("author not found"));

        Comment c = Comment.builder().content(req.content())
                .board(board).author(author).build();

        // 양방향 편의(선택)
        board.addComment(c);
        author.addComment(c);

        commentRepo.save(c);
        return ResponseEntity.ok(c.getId());
    }
}
