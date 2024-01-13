package com.example.BaiTest.services;

import com.example.BaiTest.model.Post;

public interface iPost {
    public boolean addPost(Post post);
    public boolean deletePost( int postId);
}
