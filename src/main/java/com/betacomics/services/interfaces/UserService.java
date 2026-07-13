package com.betacomics.services.interfaces;

import com.betacomics.dto.input.RegisterRequest;
import com.betacomics.dto.input.UpdateProfileRequest;
import com.betacomics.dto.output.UserDTO;

public interface UserService {
    UserDTO register(RegisterRequest request);

    UserDTO getUserById(Long id);

    UserDTO updateProfile(Long id, UpdateProfileRequest request);
}