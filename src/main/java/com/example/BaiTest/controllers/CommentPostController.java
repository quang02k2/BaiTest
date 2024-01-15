package com.example.BaiTest.controllers;

import com.example.BaiTest.dtos.CommentPostDTO;
import com.example.BaiTest.model.CommentPost;
import com.example.BaiTest.services.CommentPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/post/commentPost")
@RequiredArgsConstructor
public class CommentPostController {
    @Autowired
    private CommentPostService commentPostService;

    @PostMapping("/addCommentPost")
    public ResponseEntity<?> addPost (@RequestBody CommentPostDTO commentPostDTO){
        return commentPostService.addCommentPost(commentPostDTO);
    }
}
