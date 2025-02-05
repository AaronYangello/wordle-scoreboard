package com.wordlescoreboard.score_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wordlescoreboard.score_service.model.UserStreak;

import java.util.Optional;

@Repository
public interface UserStreakDao extends JpaRepository<UserStreak, Long> {
    Optional<UserStreak> findByUsername(String username);
}