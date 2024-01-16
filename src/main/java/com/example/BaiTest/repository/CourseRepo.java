package com.example.BaiTest.repository;

import com.example.BaiTest.model.Course;
import com.example.BaiTest.model.CourseLevel;
import com.example.BaiTest.model.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {
    List<Course> findByCourseLevel(CourseLevel courseLevel);
    List<Course> findByCourseType(CourseType courseType);
    List<Course> findByNameOfCourseContaining(String name);

}
