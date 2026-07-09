package com.betacomics.services.interfaces;

import com.betacomics.dto.output.CartDTO;

public interface CartService {
    void create();
    CartDTO getById(Long id);
    void clear(Long id); 
}