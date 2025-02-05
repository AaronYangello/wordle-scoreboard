package com.wordlescoreboard.feed_service.dao;

import com.wordlescoreboard.feed_service.model.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedDao extends JpaRepository<Feed, String> {
    List<Feed> findByUserIdsContains(String userId);
}
