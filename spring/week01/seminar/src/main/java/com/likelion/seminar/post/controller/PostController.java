package com.likelion.seminar.post.controller;


import com.likelion.seminar.post.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("post")
public class PostController{
    private final List<PostDTO> postDTOList;

    @PostMapping
    public void createPost(@RequestBody PostDTO postDto){
        System.out.println(postDto.toString());
        this.postDTOList.add(postDto);
    }

    @GetMapping()
    public List<PostDTO> readPostAll(){
        System.out.println("포스트 전체 조회");
        return this.postDTOList;
    }

    @GetMapping("{id}")
    public PostDTO readPost(@PathVariable("id") int id){
        System.out.println("포스트 단일 조회");
        return this.postDTOList.get(id);
    }

    @GetMapping("param")
    public PostDTO readPost_para(@RequestParam("id") int id){
        System.out.print("포스트 단일 조회2");
        return this.postDTOList.get(id);
    }

    @PutMapping("{id}")
    public void updatePost(
            @PathVariable("id") int id,
            @RequestBody PostDTO postDto
    ) {
        PostDTO targetPost = this.postDTOList.get(id);
        if(postDto.getTitle()!=null){
            targetPost.setTitle(postDto.getTitle());
        }
        if(postDto.getContent()!=null){
            targetPost.setContent(postDto.getContent());
        }
        if(postDto.getWriter()!=null){
            targetPost.setWriter(postDto.getWriter());
        }
        this.postDTOList.set(id, targetPost);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable("id") int id){
        this.postDTOList.remove(id);
    }


}




