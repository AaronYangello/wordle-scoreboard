package com.wordlescoreboard.leaderboard_service.service;

import com.wordlescoreboard.leaderboard_service.dao.LeaderboardDao;
import com.wordlescoreboard.leaderboard_service.model.Leaderboard;
import com.wordlescoreboard.leaderboard_service.model.LeaderboardRequest;
import com.wordlescoreboard.leaderboard_service.model.UserRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardService {

    private final LeaderboardDao leaderboardDao;

    @Autowired
    public LeaderboardService(LeaderboardDao leaderboardDao) {
        this.leaderboardDao = leaderboardDao;
    }

    public Leaderboard createLeaderboard(LeaderboardRequest request) {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.setOwnerId(request.getOwnerId());
        leaderboardDao.save(leaderboard);
        return leaderboard;
    }

    public List<Leaderboard> getAllLeaderboards() {
        return leaderboardDao.findAll();
    }

    public Leaderboard getLeaderboard(Long leaderboardId) {
        return leaderboardDao.findById(leaderboardId)
                .orElseThrow(() -> new EntityNotFoundException("Leaderboard not found"));
    }

    public void deleteLeaderboard(Long leaderboardId) {
        leaderboardDao.deleteById(leaderboardId);
    }

    public Leaderboard addUserToLeaderboard(Long leaderboardId, UserRequest userRequest) {
        Leaderboard leaderboard = getLeaderboard(leaderboardId);
        leaderboard.addUser(userRequest.getUserId());
        leaderboardDao.save(leaderboard);
        return leaderboard;
    }

    public void removeUserFromLeaderboard(Long leaderboardId, Long userId) {
        Leaderboard leaderboard = getLeaderboard(leaderboardId);
        leaderboard.removeUser(userId);
        leaderboardDao.save(leaderboard);
    }
}
