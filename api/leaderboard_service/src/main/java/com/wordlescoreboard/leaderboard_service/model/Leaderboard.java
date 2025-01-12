package com.wordlescoreboard.leaderboard_service.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Leaderboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ownerId;

    @ElementCollection
    private Set<Long> userIds = new HashSet<>();

    public void addUser(Long userId) {
        userIds.add(userId);
    }

    public void removeUser(Long userId) {
        userIds.remove(userId);
    }

    public Long getId() {
        return id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public Set<Long> getUserIds() {
        return userIds;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public void setUserIds(Set<Long> userIds) {
        this.userIds = userIds;
    }
}
