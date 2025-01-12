package com.wordlescoreboard.follower_service.controller;

import com.wordlescoreboard.follower_service.dao.FollowerDao;
import com.wordlescoreboard.follower_service.model.Follower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/followers")
public class FollowerController {

    @Autowired
    private FollowerDao followerDao;

    // POST /followers
    @PostMapping
    public ResponseEntity<?> followUser(@RequestBody Follower follower) {
        Optional<Follower> existingFollower = followerDao.findByFollowerIdAndFolloweeId(
                follower.getFollowerId(), follower.getFolloweeId()
        );
        if (existingFollower.isPresent()) {
            return ResponseEntity.badRequest().body("{\"message\": \"Follower relationship already exists\"}");
        }

        Follower savedFollower = followerDao.save(follower);
        return ResponseEntity.ok(savedFollower);
    }

    // GET /followers
    @GetMapping
    public ResponseEntity<List<Follower>> getAllFollowers() {
        List<Follower> followers = followerDao.findAll();
        return ResponseEntity.ok(followers);
    }

    // GET /followers/{follower_id}
    @GetMapping("/{follower_id}")
    public ResponseEntity<Follower> getFollowerById(@PathVariable Long follower_id) {
        Optional<Follower> follower = followerDao.findById(follower_id);
        return follower.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE /followers/{follower_id}
    @DeleteMapping("/{follower_id}")
    public ResponseEntity<?> unfollowUser(@PathVariable Long follower_id) {
        Optional<Follower> follower = followerDao.findById(follower_id);
        if (follower.isPresent()) {
            followerDao.delete(follower.get());
            return ResponseEntity.ok().body("{\"message\": \"Follower relationship deleted\"}");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}