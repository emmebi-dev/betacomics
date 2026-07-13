package com.betacomics.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacomics.dto.input.RegisterRequest;
import com.betacomics.dto.input.UpdateProfileRequest;
import com.betacomics.dto.output.UserDTO;
import com.betacomics.exceptions.ResourceNotFoundException;
import com.betacomics.models.Cart;
import com.betacomics.models.User;
import com.betacomics.repositories.UserRepository;
import com.betacomics.services.interfaces.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalStateException("Questo username è già utilizzato da un altro utente.");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("Questo indirizzo email è già registrato.");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword()) // TODO: In futuro qui si userà passwordEncoder.encode()
                .isAdmin(false)
                .build();

        Cart emptyCart = Cart.builder()
                .build();

        user.setCart(emptyCart);

        User savedUser = userRepository.save(user);

        return mapToUserDTO(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con ID: " + id));
        return mapToUserDTO(user);
    }

    @Override
    public UserDTO updateProfile(Long id, UpdateProfileRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Impossibile aggiornare: utente non trovato con ID: " + id));

        if (!user.getUsername().equalsIgnoreCase(request.getUsername()) && 
                userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalStateException("Il nuovo username inserito è già in uso.");
        }

        if (!user.getEmail().equalsIgnoreCase(request.getEmail()) && 
                userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("La nuova email inserita è già in uso.");
        }

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        User updatedUser = userRepository.save(user);
        return mapToUserDTO(updatedUser);
    }

    private UserDTO mapToUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .isAdmin(user.isAdmin())
                .build();
    }
}