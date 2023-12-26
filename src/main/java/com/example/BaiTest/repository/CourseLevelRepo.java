package com.example.BaiTest.repository;

import com.example.BaiTest.model.CourseLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseLevelRepo extends JpaRepository<CourseLevel, Integer> {
}
