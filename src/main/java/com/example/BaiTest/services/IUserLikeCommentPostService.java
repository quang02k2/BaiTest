package com.example.BaiTest.services;

import com.example.BaiTest.model.UserLikeCommentPost;
import org.springframework.http.ResponseEntity;

public interface IUserLikeCommentPostService {
    ResponseEntity<?> addUserLikeCommentPost(UserLikeCommentPost userLikeCommentPost);
}
