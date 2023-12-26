package com.example.BaiTest.repository;

import com.example.BaiTest.model.LearningExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningExperienceRepo extends JpaRepository<LearningExperience, Integer> {
}
