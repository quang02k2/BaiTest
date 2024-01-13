package com.example.BaiTest.services;

import com.example.BaiTest.model.Post;
import com.example.BaiTest.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService implements iPost {
    @Autowired
    private UserRepo userRepo;


    @Override
    public boolean addPost(Post post) {
        return false;
    }

    @Override
    public boolean deletePost(int postId) {
        return false;
    }
}
