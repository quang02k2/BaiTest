package com.example.BaiTest.services.iservices;

import com.example.BaiTest.dtos.Post.CommentPostDTO;
import org.springframework.http.ResponseEntity;

public interface ICommentPostService {
    ResponseEntity<?> addCommentPost (CommentPostDTO commentPostDTO);
    ResponseEntity<?> deleteCommentPost (int commentPostID);
}
