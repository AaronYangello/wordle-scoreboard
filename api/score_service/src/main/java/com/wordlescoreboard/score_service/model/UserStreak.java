package com.wordlescoreboard.score_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_streaks")
public class UserStreak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private int streak;

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public int getStreak() { return streak; }
    public void setStreak(int streak) { this.streak = streak; }
}

