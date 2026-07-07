package com.betacomics.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacomics.dto.output.ResponseDTO;
import com.betacomics.services.impl.ProductServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Slf4j
public class ProductController {
	
	private final ProductServiceImpl productS;
	
	@GetMapping("/list")
	public ResponseEntity<Object> list() throws Exception {
		return ResponseEntity.ok(productS.list());
	}
	
	@GetMapping("getById/{id}")
	public ResponseEntity<Object> getById(@PathVariable (required = true) Long id) throws Exception{
		return ResponseEntity.ok(productS.getById(id));
	}
	
	@DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable (required = true) Long id) throws Exception{
        productS.delete(id);
        return ResponseEntity.ok(ResponseDTO.builder()
                    .message("deleted...")
                    .build());
    }

}
