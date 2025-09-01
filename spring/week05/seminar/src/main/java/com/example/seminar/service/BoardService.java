package com.example.seminar.service;

import com.example.seminar.dto.board.*;
import com.example.seminar.entity.BoardEntity;
import com.example.seminar.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long createBoard(BoardCreateRequest req){
        BoardEntity e = BoardEntity.builder()
                .name(req.getName()).description(req.getDescription()).build();
        return boardRepository.save(e).getId();
    }
    public BoardResponse readBoard(Long id){
        BoardEntity e = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found: " + id));
        return BoardResponse.from(e);
    }
    public List<BoardResponse> readAllBoards(){
        return boardRepository.findAll().stream().map(BoardResponse::from).toList();
    }
    @Transactional
    public BoardResponse updateBoard(Long id, BoardUpdateRequest req){
        BoardEntity e = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found: " + id));
        if (req.getName()!=null) e.setName(req.getName());
        if (req.getDescription()!=null) e.setDescription(req.getDescription());
        return BoardResponse.from(e);
    }
    @Transactional
    public void deleteBoard(Long id){
        if (!boardRepository.existsById(id))
            throw new ResourceNotFoundException("Board not found: " + id);
        boardRepository.deleteById(id);
    }
}
