package com.example.BaiTest.repository;

import com.example.BaiTest.model.UserLikeCommentLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLikeCommentLessonRepo extends JpaRepository<UserLikeCommentLesson, Integer> {
}
