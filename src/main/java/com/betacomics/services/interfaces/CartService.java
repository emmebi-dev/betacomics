package com.betacomics.services.interfaces;

import com.betacomics.dto.output.CartDTO;

public interface CartService {
    CartDTO create();
    CartDTO getById(Long id);
    CartDTO clear(Long id); 
}