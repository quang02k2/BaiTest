package com.example.BaiTest.services;

import com.example.BaiTest.dtos.PostDTO;
import com.example.BaiTest.model.Post;
import com.example.BaiTest.responses.PostResponse;
import org.springframework.http.ResponseEntity;

public interface iPostService {
    public ResponseEntity<PostResponse> addPost(PostDTO postdto);
    public boolean deletePost( int postId);
}
