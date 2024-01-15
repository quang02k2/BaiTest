package com.example.BaiTest.controllers;

import com.example.BaiTest.model.UserLikeCommentPost;
import com.example.BaiTest.services.UserLikeCommentPostServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/post/UserLikeCommentPost")
@RequiredArgsConstructor
public class UserLikeCommentPostController {

    @Autowired
    private UserLikeCommentPostServices userLikeCommentPostServices;

    @PostMapping("/addUserLikeCommentPost")
    public ResponseEntity<?> addUserLikeCommentPost (@RequestBody UserLikeCommentPost userLikeCommentPost){
        return  userLikeCommentPostServices.addUserLikeCommentPost(userLikeCommentPost);
    }

}
