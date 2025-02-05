package com.wordlescoreboard.feed_service.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Feed {
    @Id
    private String feedId;
    private String ownerId;
    @ElementCollection
    private Set<String> userIds = new HashSet<>();
    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    // Constructors, getters, and setters
    public Feed(String ownerId) {
        this.feedId = UUID.randomUUID().toString();
        this.ownerId = ownerId;
        this.userIds.add(ownerId);
    }

    public Feed() {}

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Set<String> getUserIds() {
        return userIds;
    }

    public List<String> getListUserIds() {
        return new ArrayList<String>(userIds);
    }

    public void setUserIds(Set<String> userIds) {
        this.userIds = userIds;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
