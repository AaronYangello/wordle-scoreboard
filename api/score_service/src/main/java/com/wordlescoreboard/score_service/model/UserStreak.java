package com.wordlescoreboard.score_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_streaks")
public class UserStreak {

    @Id
    private Long userId;
    private int streak;

    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public int getStreak() { return streak; }
    public void setStreak(int streak) { this.streak = streak; }
}

