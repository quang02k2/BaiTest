package com.example.BaiTest.repository;

import com.example.BaiTest.model.RefreshTokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokensRepo extends JpaRepository<RefreshTokens, Integer> {
}
