package com.example.seminar.controller;

import com.example.seminar.dto.board.*;
import com.example.seminar.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody BoardCreateRequest req){
        return ResponseEntity.ok(boardService.createBoard(req));
    }
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> read(@PathVariable Long id){
        return ResponseEntity.ok(boardService.readBoard(id));
    }
    @GetMapping
    public ResponseEntity<List<BoardResponse>> readAll(){
        return ResponseEntity.ok(boardService.readAllBoards());
    }
    @PutMapping("/{id}")
    public ResponseEntity<BoardResponse> update(@PathVariable Long id,
                                                @RequestBody BoardUpdateRequest req){
        return ResponseEntity.ok(boardService.updateBoard(id, req));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        boardService.deleteBoard(id);
        return ResponseEntity.ok().build();
    }
}
