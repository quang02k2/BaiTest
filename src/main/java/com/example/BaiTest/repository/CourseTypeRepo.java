package com.example.BaiTest.repository;

import com.example.BaiTest.model.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTypeRepo extends JpaRepository<CourseType, Integer> {
}
