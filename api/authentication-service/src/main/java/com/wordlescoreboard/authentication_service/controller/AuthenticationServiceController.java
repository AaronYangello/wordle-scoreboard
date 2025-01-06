package com.wordlescoreboard.authentication_service.controller;

import com.wordlescoreboard.authentication_service.model.*;
import com.wordlescoreboard.authentication_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api/auth")
public class AuthenticationServiceController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        if (userService.usernameExists(userRegistrationRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken");
        }
        if (userService.emailExists(userRegistrationRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use");
        }

        User user = new User();
        user.setUsername(userRegistrationRequest.getUsername());
        user.setEmail(userRegistrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByUsername(loginRequest.getUsername());
        if (user == null ||!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().build();
        }
        String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @PutMapping("/users/{username}")
    public ResponseEntity<String> updateUser(@PathVariable String username, @RequestBody UserUpdateRequest userUpdateRequest) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        user.setEmail(userUpdateRequest.getEmail());
        userService.saveUser(user);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        userService.deleteUser(user);
        return ResponseEntity.ok("User deleted successfully");
    }
}