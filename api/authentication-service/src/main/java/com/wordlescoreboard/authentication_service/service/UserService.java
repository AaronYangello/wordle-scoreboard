package com.wordlescoreboard.authentication_service.service;

import com.wordlescoreboard.authentication_service.dao.UserDao;
import com.wordlescoreboard.authentication_service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User saveUser(User user) {
        return userDao.save(user);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }

    public boolean usernameExists(String username) {
        return userDao.existsByUsername(username);
    }

    public boolean emailExists(String email) {
        return userDao.existsByEmail(email);
    }
}
