package com.wordlescoreboard.feed_service.dao;

import com.wordlescoreboard.feed_service.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDao extends JpaRepository<Post, String> {}
