package com.betacomics.services.interfaces;

import java.util.List;

import com.betacomics.dto.output.ProductDTO;

public interface ProductService {
	public void delete(Long id);
	public ProductDTO getById(Long id);
	public List<ProductDTO> list();
}
