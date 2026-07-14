package com.betacomics.maps;

import java.util.List;
import com.betacomics.dto.output.ProductDTO;
import com.betacomics.models.Product;
import com.betacomics.models.Comic;
import com.betacomics.models.BoardGame;
import com.betacomics.models.ActionFigure;

public class ProductMap {

    public static List<ProductDTO> buildProductDTOList(List<? extends Product> lP) {
        if (lP == null) {
            return List.of();
        }
        return lP.stream()
                .map(ProductMap::buildProductDTO)
                .toList();
    }
    
    public static ProductDTO buildProductDTO(Product p) {
        if (p == null) {
            return null;
        }
        
        if (p instanceof Comic c) {
            return ComicMap.buildComicDTO(c);
        }
        if (p instanceof BoardGame b) {
            return BoardGameMap.buildBoardGameDTO(b);
        }
        if (p instanceof ActionFigure a) {
            return ActionFigureMap.buildActionFigureDTO(a);
        }
        
        return ProductDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .stockQuantity(p.getStockQuantity())
                .imageUrl(p.getImageUrl())
                .weight(p.getWeight())
                .releaseDate(p.getReleaseDate())
                .productType("GENERIC")
                .build();
    }
}