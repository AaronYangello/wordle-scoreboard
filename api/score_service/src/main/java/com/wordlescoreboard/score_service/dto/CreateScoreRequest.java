package com.wordlescoreboard.score_service.dto;

public class CreateScoreRequest {
    private String username;
    private String wordleShareText;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUserId(Long userId) {
        this.username = username;
    }

    public String getWordleShareText() {
        return wordleShareText;
    }

    public void setWordleShareText(String wordleShareText) {
        this.wordleShareText = wordleShareText;
    }
}