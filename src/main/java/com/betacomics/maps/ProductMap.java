package com.betacomics.maps;

import java.util.List;

import com.betacomics.dto.output.ProductDTO;
import com.betacomics.models.Product;

public class ProductMap {
	public static List<ProductDTO> buildProductDTOList(List<Product> lP){
		return lP.stream()
				.map(p -> buildProductDTO(p)
						).toList();
	};
	
	public static ProductDTO buildProductDTO(Product p) {
		return ProductDTO.builder()
				.id(p.getId())
				.name(p.getName())
				.description(p.getDescription())
				.price(p.getPrice())
				.stockQuantity(p.getStockQuantity())
				.imageUrl(p.getImageUrl())
				.weight(p.getWeight())
				.releaseDate(p.getReleaseDate())
				.build();
	}
}
