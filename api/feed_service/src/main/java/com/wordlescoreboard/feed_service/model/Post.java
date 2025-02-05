package com.wordlescoreboard.feed_service.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
public class Post {
    @Id
    private String postId;
    private String postingUserId;
    private String content;
    @ElementCollection
    private List<String> tags;
    private LocalDateTime createdAt;

    // Getters and setters
    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostingUserId() {
        return postingUserId;
    }

    public void setPostingUserId(String postingUserId) {
        this.postingUserId = postingUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}