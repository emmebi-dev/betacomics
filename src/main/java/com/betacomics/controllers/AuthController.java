package com.betacomics.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacomics.dto.input.LoginRequest;
import com.betacomics.dto.output.AuthResponseDTO;
import com.betacomics.security.JwtService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Espone /api/v1/auth/login: verifica le credenziali tramite l'AuthenticationManager
 * (che a sua volta usa CustomUserDetailsService + PasswordEncoder, invariati)
 * e restituisce un JWT invece di creare una sessione HTTP.
 *
 * Non esiste piu' un endpoint di logout lato server: con token stateless
 * la "disconnessione" e' responsabilita' del client, che scarta il token.
 * Se in futuro servisse una revoca immediata, andra' introdotta una blacklist
 * (es. su Redis) dei token invalidati.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);

        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("");

        AuthResponseDTO response = AuthResponseDTO.builder()
                .accessToken(token)
                .username(userDetails.getUsername())
                .role(role)
                .build();

        return ResponseEntity.ok(response);
    }
}