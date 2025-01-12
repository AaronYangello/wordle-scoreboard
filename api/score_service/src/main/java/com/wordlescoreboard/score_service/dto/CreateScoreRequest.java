package com.wordlescoreboard.score_service.dto;

public class CreateScoreRequest {
    private Long userId;
    private String wordleShareText;

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getWordleShareText() {
        return wordleShareText;
    }

    public void setWordleShareText(String wordleShareText) {
        this.wordleShareText = wordleShareText;
    }
}