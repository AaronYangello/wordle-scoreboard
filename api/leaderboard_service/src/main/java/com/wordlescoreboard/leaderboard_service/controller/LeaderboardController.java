package com.wordlescoreboard.leaderboard_service.controller;

import com.wordlescoreboard.leaderboard_service.model.Leaderboard;
import com.wordlescoreboard.leaderboard_service.model.LeaderboardRequest;
import com.wordlescoreboard.leaderboard_service.model.UserRequest;
import com.wordlescoreboard.leaderboard_service.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api/leaderboards")
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderboardService;

    // POST /leaderboards: Create a new leaderboard
    @PostMapping
    public ResponseEntity<Leaderboard> createLeaderboard(@RequestBody LeaderboardRequest request) {
        if (request.getOwnerId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Leaderboard leaderboard = leaderboardService.createLeaderboard(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(leaderboard);
    }

    // GET /leaderboards: Get a list of all leaderboards
    @GetMapping
    public ResponseEntity<List<Leaderboard>> getAllLeaderboards() {
        List<Leaderboard> leaderboards = leaderboardService.getAllLeaderboards();
        return ResponseEntity.ok(leaderboards);
    }

    // GET /leaderboards/{leaderboard_id}: Get a specific leaderboard
    @GetMapping("/{leaderboardId}")
    public ResponseEntity<Leaderboard> getLeaderboard(@PathVariable Long leaderboardId) {
        Leaderboard leaderboard = leaderboardService.getLeaderboard(leaderboardId);
        return ResponseEntity.ok(leaderboard);
    }

    // DELETE /leaderboards/{leaderboard_id}: Delete a specific leaderboard
    @DeleteMapping("/{leaderboardId}")
    public ResponseEntity<Map<String, String>> deleteLeaderboard(@PathVariable Long leaderboardId) {
        leaderboardService.deleteLeaderboard(leaderboardId);
        return ResponseEntity.ok(Collections.singletonMap("message", "Leaderboard deleted"));
    }

    // PUT /leaderboards/{leaderboard_id}: Add user to a specific leaderboard
    @PutMapping("/{leaderboardId}")
    public ResponseEntity<Leaderboard> addUserToLeaderboard(
            @PathVariable Long leaderboardId,
            @RequestBody UserRequest userRequest) {
        Leaderboard updatedLeaderboard = leaderboardService.addUserToLeaderboard(leaderboardId, userRequest);
        return ResponseEntity.ok(updatedLeaderboard);
    }

    // DELETE /leaderboards/{leaderboard_id}/users/{user_id}: Delete a user from a specific leaderboard
    @DeleteMapping("/{leaderboardId}/users/{userId}")
    public ResponseEntity<Map<String, String>> removeUserFromLeaderboard(
            @PathVariable Long leaderboardId,
            @PathVariable Long userId) {
        leaderboardService.removeUserFromLeaderboard(leaderboardId, userId);
        return ResponseEntity.ok(Collections.singletonMap("message", "User removed from leaderboard"));
    }
}