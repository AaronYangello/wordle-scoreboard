package com.wordlescoreboard.leaderboard_service.dao;

import com.wordlescoreboard.leaderboard_service.model.Leaderboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaderboardDao extends JpaRepository<Leaderboard, Long> {
}
