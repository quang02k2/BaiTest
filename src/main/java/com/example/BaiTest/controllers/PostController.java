package com.example.BaiTest.controllers;

import com.example.BaiTest.dtos.PostDTO;
import com.example.BaiTest.responses.LoginResponse;
import com.example.BaiTest.responses.PostResponse;
import com.example.BaiTest.services.PostService;
import com.example.BaiTest.utils.MessageKeys;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/post")
@RequiredArgsConstructor
public class PostController {
    @Autowired
    private  PostService postService;


    @PostMapping("/addPost")
    public ResponseEntity<?> addPost(@Valid @RequestBody PostDTO post) {
        try {
            //service chi tra ve du lieu entity model, dong goi response entity lam o controller
            return postService.addPost(post);


        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());

        }
    }


}
