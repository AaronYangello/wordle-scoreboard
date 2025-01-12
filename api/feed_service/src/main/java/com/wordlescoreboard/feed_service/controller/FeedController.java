package com.wordlescoreboard.feed_service.controller;

import com.wordlescoreboard.feed_service.model.Feed;
import com.wordlescoreboard.feed_service.model.Post;
import com.wordlescoreboard.feed_service.service.FeedService;
import com.wordlescoreboard.feed_service.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/feeds")
public class FeedController {

    private final FeedService feedService;
    private final PostService postService;

    public FeedController(FeedService feedService, PostService postService) {
        this.feedService = feedService;
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Feed> createFeed(@RequestBody Map<String, String> body) {
        String ownerId = body.get("owner_id");
        return ResponseEntity.ok(feedService.createFeed(ownerId));
    }

    @GetMapping
    public ResponseEntity<List<Feed>> getAllFeeds() {
        return ResponseEntity.ok(feedService.getAllFeeds());
    }

    @GetMapping("/{feed_id}")
    public ResponseEntity<Feed> getFeed(@PathVariable("feed_id") String feedId) {
        return feedService.getFeed(feedId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{feed_id}")
    public ResponseEntity<Feed> addUserToFeed(@PathVariable("feed_id") String feedId, @RequestBody Map<String, String> body) {
        String userId = body.get("user_id");
        return ResponseEntity.ok(feedService.addUserToFeed(feedId, userId));
    }

    @DeleteMapping("/{feed_id}/users/{user_id}")
    public ResponseEntity<Map<String, String>> removeUserFromFeed(@PathVariable("feed_id") String feedId, @PathVariable("user_id") String userId) {
        feedService.removeUserFromFeed(feedId, userId);
        return ResponseEntity.ok(Map.of("message", "User removed from feed"));
    }

    @PostMapping("/{feed_id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable("feed_id") String feedId, @RequestBody Post newPost) {
        return feedService.getFeed(feedId).map(feed -> ResponseEntity.ok(postService.createPost(feed, newPost)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{feed_id}/posts")
    public ResponseEntity<List<Post>> getPosts(@PathVariable("feed_id") String feedId) {
        return feedService.getFeed(feedId).map(feed -> ResponseEntity.ok(postService.getPosts(feed)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{feed_id}/posts/{post_id}")
    public ResponseEntity<Post> getPost(@PathVariable("feed_id") String feedId, @PathVariable("post_id") String postId) {
        return postService.getPost(postId).filter(post -> post.getFeed().getFeedId().equals(feedId))
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{feed_id}/posts/{post_id}")
    public ResponseEntity<Post> updatePost(@PathVariable("feed_id") String feedId, @PathVariable("post_id") String postId, @RequestBody Post updatedPost) {
        return postService.getPost(postId).filter(post -> post.getFeed().getFeedId().equals(feedId))
                .map(post -> ResponseEntity.ok(postService.updatePost(post, updatedPost)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{feed_id}/posts/{post_id}")
    public ResponseEntity<Map<String, String>> deletePost(@PathVariable("feed_id") String feedId, @PathVariable("post_id") String postId) {
        return postService.getPost(postId).filter(post -> post.getFeed().getFeedId().equals(feedId))
                .map(post -> {
                    postService.deletePost(post);
                    return ResponseEntity.ok(Map.of("message", "Feed post deleted"));
                }).orElse(ResponseEntity.notFound().build());
    }
}