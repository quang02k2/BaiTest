package com.example.BaiTest.repository;

import com.example.BaiTest.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepo extends JpaRepository<Lesson, Integer> {
}
