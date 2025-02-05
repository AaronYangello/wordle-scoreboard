package com.wordlescoreboard.score_service.service;

import com.wordlescoreboard.score_service.dao.UserStreakDao;
import com.wordlescoreboard.score_service.model.Score;
import com.wordlescoreboard.score_service.dao.ScoreDao;
import com.wordlescoreboard.score_service.model.UserStreak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ScoreService {
    private static final Logger logger = LoggerFactory.getLogger(ScoreService.class);

    @Autowired
    private ScoreDao scoreDao;
    @Autowired
    private UserStreakDao userStreakDao;

    public Score createScore(String wordleShareText, String username) {
        Score score = createScoreFromWordleShare(wordleShareText, username);

        return scoreDao.save(score);
    }

    public Score createScoreFromWordleShare(String wordleShareText, String username) {
        logger.debug("Wordle Text: {}", wordleShareText);

        wordleShareText = wordleShareText.replace("\\n", "\n");
        String[] lines = wordleShareText.split("\\R");
        String header = lines[0];
        logger.debug("Header: {}", header);

        Long gameId = Long.parseLong(header.split(" ")[1].replace(",", ""));
        Long todaysGameId = calculateTodaysGameId();
        if (!todaysGameId.equals(gameId)) {
            throw new IllegalArgumentException("Game ID is for a previous day. Please enter the score for game " + todaysGameId.toString());
        }
        // Check if a score already exists for this user and game
        Optional<Score> existingScore = scoreDao.findByUsernameAndGameId(username, gameId);
        if (existingScore.isPresent()) {
            throw new IllegalArgumentException("Score for this game already exists for the user.");
        }

        // Fetch user streak
        UserStreak userStreak = userStreakDao.findByUsername(username)
                .orElseGet(() -> {
                    UserStreak newStreak = new UserStreak();
                    newStreak.setUsername(username);
                    newStreak.setStreak(0);
                    return userStreakDao.save(newStreak);
                });

        int streak = userStreak.getStreak();
        double totalScore = calculateScore(wordleShareText, streak);

        userStreak.setStreak(streak + 1);
        userStreakDao.save(userStreak);

        // Create score entity
        Score score = new Score();
        score.setusername(username);
        score.setGameId(Long.parseLong(header.split(" ")[1].replace(",", "")));
        score.setScore(totalScore);
        return score;
    }

    public double calculateScore(String wordleShareText, int streak) {
        String[] lines = wordleShareText.split("\\n");
        String header = lines[0];
        boolean failed = header.contains("X");

        int guesses = failed ? 6 : Integer.parseInt(header.split(" ")[2].split("/")[0]);
        int basePoints = failed ? 0 : (6 - guesses + 1) * 10;;
        return basePoints + calculateYellowPoints(lines) + calculateGreenPoints(lines) + getStreakBonus(streak);
    }

    private int calculateYellowPoints(String[] lines) {
        int points = 0;
        for (int i = 1; i < lines.length; i++) {
            int yellowSquares = countOccurrences(lines[i], "🟨");
            points += yellowSquares * getYellowSquarePoints(i);
        }
        return points;
    }

    private int calculateGreenPoints(String[] lines) {
        int points = 0;
        for (int i = 1; i < lines.length; i++) {
            int greenSquares = countOccurrences(lines[i], "🟩");
            points += greenSquares * getGreenSquarePoints(i);
        }
        return points;
    }

    private int getYellowSquarePoints(int guessNumber) {
        switch (guessNumber) {
            case 1: return 5;
            case 2: return 4;
            case 3: return 3;
            case 4: return 2;
            case 5:
            case 6: return 1;
            default: return 0;
        }
    }

    private int getGreenSquarePoints(int guessNumber) {
        switch (guessNumber) {
            case 1: return 10;
            case 2: return 8;
            case 3: return 6;
            case 4: return 4;
            case 5:
            case 6: return 2;
            default: return 0;
        }
    }

    private int countOccurrences(String line, String symbol) {
        int count = 0;
        int index = 0;
        while ((index = line.indexOf(symbol, index)) != -1) {
            count++;
            index += symbol.length(); // Move past the found symbol
        }
        return count;
    }

    private int getStreakBonus(int streak){
        int bonus = 0;
        if(streak >= 100){
            bonus = 10 * streak % 50;
        }
        else if (streak >= 50){
            bonus = 8;
        }
        else if (streak >= 30){
            bonus = 5;
        }
        else if (streak >= 10){
            bonus = 3;
        }
        return bonus;
    }

    private static long calculateTodaysGameId() {
        // First Wordle: June 20, 2021
        // Epoch: 1 January 1970
        ZonedDateTime firstWordleDateTime = ZonedDateTime.of(2021, 6, 20, 0, 0, 0, 0, ZoneId.of("America/New_York"));
        ZonedDateTime currentDateTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        ZonedDateTime epochDateTime = ZonedDateTime.ofInstant(Instant.EPOCH, ZoneId.of("America/New_York"));

        long firstWordleDaysSinceEpoch = ChronoUnit.DAYS.between(epochDateTime, firstWordleDateTime);
        long todayDaysSinceEpoch = ChronoUnit.DAYS.between(epochDateTime, currentDateTime);

        return todayDaysSinceEpoch - firstWordleDaysSinceEpoch + 1;
    }

    public List<Score> getAllScores() {
        return scoreDao.findAll();
    }

    public Optional<Score> getScoreById(Long scoreId) {
        return scoreDao.findById(scoreId);
    }

    public List<Score> getScoresByUsername(String username) {
        return scoreDao.findByUsername(username);
    }

    public List<Score> getScoresByGameId(Long gameId) {
        return scoreDao.findByGameId(gameId);
    }

    public Score updateScore(Long scoreId, Double scoreValue) {
        Score score = scoreDao.findById(scoreId).orElseThrow(() -> new RuntimeException("Score not found"));
        score.setScore(scoreValue);
        return scoreDao.save(score);
    }

    public void deleteScore(Long scoreId) {
        scoreDao.deleteById(scoreId);
    }
}