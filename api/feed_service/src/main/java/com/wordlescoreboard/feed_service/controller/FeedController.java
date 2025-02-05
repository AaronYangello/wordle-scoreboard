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

    //Create a new feed
    @PostMapping
    public ResponseEntity<Feed> createFeed(@RequestBody Map<String, String> body) {
        String ownerId = body.get("owner_id");
        return ResponseEntity.ok(feedService.createFeed(ownerId));
    }

    //Get all feeds
    @GetMapping
    public ResponseEntity<List<Feed>> getAllFeeds() {
        return ResponseEntity.ok(feedService.getAllFeeds());
    }

    //Get a specific feed
    @GetMapping("/{feed_id}")
    public ResponseEntity<Feed> getFeed(@PathVariable("feed_id") String feedId) {
        return feedService.getFeed(feedId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //Get users on a specific feed
    @PutMapping("/{feed_id}/users")
    public ResponseEntity<Feed> addUserToFeed(@PathVariable("feed_id") String feedId, @RequestBody Map<String, String> body) {
        String userId = body.get("user_id");
        return ResponseEntity.ok(feedService.addUserToFeed(feedId, userId));
    }

    //Remove user from a specific feed
    @DeleteMapping("/{feed_id}/users/{user_id}")
    public ResponseEntity<Map<String, String>> removeUserFromFeed(@PathVariable("feed_id") String feedId, @PathVariable("user_id") String userId) {
        feedService.removeUserFromFeed(feedId, userId);
        return ResponseEntity.ok(Map.of("message", "User removed from feed"));
    }

    //Create a new post
    @PostMapping("/users/{user_id}/posts")
    public ResponseEntity<Post> createPostForUser(@PathVariable("user_id") String userId, @RequestBody Post newPost) {
        return ResponseEntity.ok(postService.createPost(newPost));
    }

    //Get all posts from a specific user
    @GetMapping("/users/{user_id}/posts")
    public ResponseEntity<List<Post>> getPostsForUser(@PathVariable("user_id") String userId) {
        return ResponseEntity.ok(postService.getPostsForUser(userId));
    }

    //Get all posts on a specific feed
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPostsFromUsers(@RequestBody List<String> userIds) {
        return ResponseEntity.ok(postService.getPosts(userIds));
    }

    //Get all posts on a specific feed
    @GetMapping("/{feed_id}/posts")
    public ResponseEntity<List<Post>> getPosts(@PathVariable("feed_id") String feedId) {
        return feedService.getFeed(feedId).map(feed -> ResponseEntity.ok(postService.getPosts(feed)))
                .orElse(ResponseEntity.notFound().build());
    }

    //Get a specific post
    @GetMapping("/posts/{post_id}")
    public ResponseEntity<Post> getPost(@PathVariable("post_id") String postId) {
        return postService.getPost(postId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //Delete a specific post
    @DeleteMapping("/posts/{post_id}")
    public ResponseEntity<Map<String, String>> deletePost(@PathVariable("feed_id") String feedId, @PathVariable("post_id") String postId) {
        return postService.getPost(postId)
                .map(post -> {
                    postService.deletePost(post);
                    return ResponseEntity.ok(Map.of("message", "Feed post deleted"));
                }).orElse(ResponseEntity.notFound().build());
    }
}