package com.example.BaiTest.repository;

import com.example.BaiTest.model.UserLessonCheckpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLessonCheckpointRepo extends JpaRepository<UserLessonCheckpoint, Integer> {
}
