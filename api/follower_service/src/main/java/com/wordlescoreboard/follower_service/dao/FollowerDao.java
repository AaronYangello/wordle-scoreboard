package com.wordlescoreboard.follower_service.dao;

import com.wordlescoreboard.follower_service.model.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowerDao extends JpaRepository<Follower, Long> {
    Optional<Follower> findByFollowerIdAndFolloweeId(Long follower_id, Long followee_id);
}