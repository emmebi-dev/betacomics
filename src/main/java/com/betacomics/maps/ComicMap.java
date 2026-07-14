package com.betacomics.maps;

import java.util.List;
import com.betacomics.dto.output.ComicDTO;
import com.betacomics.models.Comic;

public class ComicMap {

    public static List<ComicDTO> buildComicDTOList(List<Comic> lC) {
        if (lC == null) {
            return List.of();
        }
        return lC.stream()
                .map(ComicMap::buildComicDTO)
                .toList();
    }
    
    public static ComicDTO buildComicDTO(Comic c) {
        if (c == null) {
            return null;
        }
        return ComicDTO.builder()
                .id(c.getId())
                .name(c.getName())
                .description(c.getDescription())
                .price(c.getPrice())
                .stockQuantity(c.getStockQuantity())
                .imageUrl(c.getImageUrl())
                .weight(c.getWeight())
                .releaseDate(c.getReleaseDate())
                .productType("COMIC")
                .author(c.getAuthor())
                .publisher(c.getPublisher())
                .volumeNumber(c.getVolumeNumber())
                .pages(c.getPages())
                .build();
    }
}