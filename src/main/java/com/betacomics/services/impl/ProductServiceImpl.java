package com.betacomics.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacomics.dto.output.ProductDTO;
import com.betacomics.maps.ProductMap;
import com.betacomics.repositories.ProductRepository;
import com.betacomics.services.interfaces.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository pRep;
    
    @Transactional
    @Override
    public void delete(Long id) {
        log.debug("delete product: {}", id);
        if (!pRep.existsById(id)) {
            throw new RuntimeException("Product not found at id: " + id);
        }
        pRep.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO getById(Long id) {
        log.debug("get product by id: {}", id);
        return pRep.findById(id)
                .map(ProductMap::buildProductDTO)
                .orElseThrow(() -> new RuntimeException("Product not found at id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDTO> list(Pageable pageable) {
        log.debug("list products paginated: {}", pageable);
        return pRep.findAll(pageable).map(ProductMap::buildProductDTO);
    }
}