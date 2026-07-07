package com.betacomics.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

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
	
	@Override
	public void delete(Long id) {
		log.debug("delete {}", id);
		pRep.deleteById(id);
	}

	@Override
	public ProductDTO getById(Long id) {
		log.debug("get by id {}", id);
		return ProductMap.buildProductDTO(pRep.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found at id: " + id)));
	}

	@Override
	public List<ProductDTO> list() {
		log.debug("list");
		return ProductMap.buildProductDTOList(pRep.findAll());
	}

}
