package com.wordlescoreboard.feed_service.service;

import com.wordlescoreboard.feed_service.dao.PostDao;
import com.wordlescoreboard.feed_service.model.Feed;
import com.wordlescoreboard.feed_service.model.Post;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {
    private final PostDao postDao;

    public PostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public Post createPost(Feed feed, Post newPost) {
        newPost.setPostId(UUID.randomUUID().toString());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setFeed(feed);
        return postDao.save(newPost);
    }

    public List<Post> getPosts(Feed feed) {
        return feed.getPosts();
    }

    public Optional<Post> getPost(String postId) {
        return postDao.findById(postId);
    }

    public Post updatePost(Post post, Post updatedPost) {
        post.setContent(updatedPost.getContent());
        post.setTags(updatedPost.getTags());
        return postDao.save(post);
    }

    public void deletePost(Post post) {
        postDao.delete(post);
    }
}