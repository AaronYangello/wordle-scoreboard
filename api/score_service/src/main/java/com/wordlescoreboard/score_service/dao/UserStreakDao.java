package com.wordlescoreboard.score_service.dao;

import com.wordlescoreboard.score_service.model.UserStreak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStreakDao extends JpaRepository<com.wordlescoreboard.score_service.model.UserStreak, Long> {
}