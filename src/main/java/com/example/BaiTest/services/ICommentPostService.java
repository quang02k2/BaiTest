package com.example.BaiTest.services;

import com.example.BaiTest.dtos.CommentPostDTO;
import com.example.BaiTest.model.CommentPost;
import com.example.BaiTest.responses.CommentPostResponse;
import org.springframework.http.ResponseEntity;

public interface ICommentPostService {
    ResponseEntity<?> addCommentPost (CommentPostDTO commentPostDTO);
    ResponseEntity<?> deleteCommentPost (int commentPostID);
}
