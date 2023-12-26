package com.example.BaiTest.repository;

import com.example.BaiTest.model.CommentPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentPostRepo extends JpaRepository<CommentPost, Integer> {
}
