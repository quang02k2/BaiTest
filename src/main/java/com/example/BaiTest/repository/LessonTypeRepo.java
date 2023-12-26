package com.example.BaiTest.repository;

import com.example.BaiTest.model.LessonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonTypeRepo extends JpaRepository<LessonType, Integer> {
}
