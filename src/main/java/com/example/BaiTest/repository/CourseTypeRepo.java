package com.example.BaiTest.repository;

import com.example.BaiTest.model.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseTypeRepo extends JpaRepository<CourseType, Integer> {
    List<CourseType> findByNameContaining(String name);

}
