package com.betacomics.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacomics.dto.output.ProductDTO;
import com.betacomics.dto.output.ResponseDTO;
import com.betacomics.services.interfaces.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Slf4j
public class ProductController {
    
    private final ProductService productService;
    
    @GetMapping("/list")
    public ResponseEntity<Page<ProductDTO>> list(Pageable pageable) {
        log.debug("REST request to get a page of Products");
        return ResponseEntity.ok(productService.list(pageable));
    }
    
    @GetMapping("/getById/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        log.debug("REST request to get Product : {}", id);
        return ResponseEntity.ok(productService.getById(id));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Long id) {
        log.debug("REST request to delete Product : {}", id);
        productService.delete(id);
        return ResponseEntity.ok(ResponseDTO.builder()
                    .message("Product successfully deleted")
                    .build());
    }
}