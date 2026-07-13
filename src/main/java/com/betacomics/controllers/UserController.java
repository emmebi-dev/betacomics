package com.betacomics.controllers;

import com.betacomics.dto.input.RegisterRequest;
import com.betacomics.dto.input.UpdateProfileRequest;
import com.betacomics.dto.output.UserDTO;
import com.betacomics.services.interfaces.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterRequest request) {
        UserDTO newUser = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getMyProfile() {
        Long currentUserId = getCurrentUserId(); // Simulazione dell'estrazione dal token JWT
        UserDTO profile = userService.getUserById(currentUserId);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserDTO> updateMyProfile(@Valid @RequestBody UpdateProfileRequest request) {
        Long currentUserId = getCurrentUserId();
        UserDTO updatedProfile = userService.updateProfile(currentUserId, request);
        return ResponseEntity.ok(updatedProfile);
    }

    private Long getCurrentUserId() {
        return 1L;
    }
}