package com.example.BaiTest.repository;

import com.example.BaiTest.model.CommentLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLessonRepo extends JpaRepository<CommentLesson, Integer> {
}
