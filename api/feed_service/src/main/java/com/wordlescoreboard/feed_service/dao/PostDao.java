package com.wordlescoreboard.feed_service.dao;

import com.wordlescoreboard.feed_service.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostDao extends JpaRepository<Post, String> {
    List<Post> findByPostingUserId(String postingUserId);
    List<Post> findByPostingUserIdInOrderByCreatedAt(List<String> userIds);
}
