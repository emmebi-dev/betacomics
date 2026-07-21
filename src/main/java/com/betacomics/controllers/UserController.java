package com.betacomics.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacomics.dto.input.RegisterRequest;
import com.betacomics.dto.input.UpdateProfileRequest;
import com.betacomics.dto.output.UserDTO;
import com.betacomics.security.CurrentUserProvider;
import com.betacomics.services.interfaces.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CurrentUserProvider currentUserProvider;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterRequest request) {
        UserDTO newUser = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getMyProfile() {
        Long currentUserId = currentUserProvider.getCurrentUserId();
        UserDTO profile = userService.getUserById(currentUserId);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserDTO> updateMyProfile(@Valid @RequestBody UpdateProfileRequest request) {
        Long currentUserId = currentUserProvider.getCurrentUserId();
        UserDTO updatedProfile = userService.updateProfile(currentUserId, request);
        return ResponseEntity.ok(updatedProfile);
    }
}