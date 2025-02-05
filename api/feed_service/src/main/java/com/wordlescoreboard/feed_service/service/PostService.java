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

    public Post createPost(Post newPost) {
        newPost.setPostId(UUID.randomUUID().toString());
        newPost.setCreatedAt(LocalDateTime.now());
        return postDao.save(newPost);
    }

    public Optional<Post> getPost(String postId) {
        return postDao.findById(postId);
    }

    public List<Post> getPostsForUser(String userId){
        return postDao.findByPostingUserId(userId);
    }

    public Post updatePost(Post post, Post updatedPost) {
        post.setContent(updatedPost.getContent());
        post.setTags(updatedPost.getTags());
        return postDao.save(post);
    }

    public void deletePost(Post post) {
        postDao.delete(post);
    }

    public List<Post> getPosts(Feed feed){
        return postDao.findByPostingUserIdInOrderByCreatedAt(feed.getListUserIds());
    }

    public List<Post> getPosts(List<String> userIds){
        return postDao.findByPostingUserIdInOrderByCreatedAt(userIds);
    }
}