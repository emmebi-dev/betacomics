package com.betacomics.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.betacomics.dto.output.ProductDTO;

public interface ProductService {
    
    ProductDTO getById(Long id);
    
    Page<ProductDTO> list(Pageable pageable);
    
    void delete(Long id);
}