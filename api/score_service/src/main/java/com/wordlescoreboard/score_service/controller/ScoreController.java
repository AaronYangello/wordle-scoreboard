package com.wordlescoreboard.score_service.controller;


import com.wordlescoreboard.score_service.dto.CreateScoreRequest;
import com.wordlescoreboard.score_service.model.Score;
import com.wordlescoreboard.score_service.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping
    public ResponseEntity<List<Score>> getAllScores() {
        return ResponseEntity.ok(scoreService.getAllScores());
    }

    @PostMapping
    public ResponseEntity<Score> createScore(@RequestBody CreateScoreRequest request) {
        return ResponseEntity.ok(scoreService.createScore(request.getWordleShareText(), request.getUserId()));
    }

    @GetMapping("/{scoreId}")
    public ResponseEntity<Score> getScoreById(@PathVariable Long scoreId) {
        return scoreService.getScoreById(scoreId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Score>> getScoresByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(scoreService.getScoresByUserId(userId));
    }

    @GetMapping("/games/{gameId}")
    public ResponseEntity<List<Score>> getScoresByGameId(@PathVariable Long gameId) {
        return ResponseEntity.ok(scoreService.getScoresByGameId(gameId));
    }

    @PutMapping("/{scoreId}")
    public ResponseEntity<Score> updateScore(@PathVariable Long scoreId, @RequestBody Score score) {
        return ResponseEntity.ok(scoreService.updateScore(scoreId, score.getScore()));
    }

    @DeleteMapping("/{scoreId}")
    public ResponseEntity<Void> deleteScore(@PathVariable Long scoreId) {
        scoreService.deleteScore(scoreId);
        return ResponseEntity.ok().build();
    }
}