package com.wordlescoreboard.feed_service.service;

import com.wordlescoreboard.feed_service.dao.FeedDao;
import com.wordlescoreboard.feed_service.model.Feed;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FeedService {
    private final FeedDao feedDao;

    public FeedService(FeedDao feedDao) {
        this.feedDao = feedDao;
    }

    public Feed createFeed(String ownerId) {
        Feed feed = new Feed(ownerId);
        return feedDao.save(feed);
    }

    public List<Feed> getAllFeeds() {
        return feedDao.findAll();
    }

    public List<Feed> getAllFeedsForUser(String userId) {
        return feedDao.findByUserIdsContains(userId);
    }

    public Optional<Feed> getFeed(String feedId) {
        return feedDao.findById(feedId);
    }

    public Feed addUserToFeed(String feedId, String userId) {
        return feedDao.findById(feedId).map(feed -> {
            feed.getUserIds().add(userId);
            return feedDao.save(feed);
        }).orElseThrow(() -> new NoSuchElementException("Feed not found"));
    }

    public void removeUserFromFeed(String feedId, String userId) {
        feedDao.findById(feedId).ifPresent(feed -> {
            feed.getUserIds().remove(userId);
            feedDao.save(feed);
        });
    }
}