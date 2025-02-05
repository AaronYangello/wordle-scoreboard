package com.wordlescoreboard.score_service.dao;

import com.wordlescoreboard.score_service.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreDao extends JpaRepository<Score, Long> {
    List<Score> findByUsername(String username);
    List<Score> findByGameId(Long gameId);
    Optional<Score> findByUsernameAndGameId(String username, Long gameId);
}
