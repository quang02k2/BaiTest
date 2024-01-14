package com.example.BaiTest.repository;

import com.example.BaiTest.model.PostSentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PostSentenceRepo extends JpaRepository<PostSentence, Integer> {
    ResponseEntity<?> deleteAllByPostId(int id);
}