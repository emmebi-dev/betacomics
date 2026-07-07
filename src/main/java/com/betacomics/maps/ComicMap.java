package com.betacomics.maps;

import java.util.List;

import com.betacomics.dto.output.ComicDTO;
import com.betacomics.models.Comic;

public class ComicMap {
	public static List<ComicDTO> buildComicDTOList(List<Comic> lC){
		return lC.stream()
				.map(c -> buildComicDTO(c)
						).toList();
	};
	
	public static ComicDTO buildComicDTO(Comic c) {
		return ComicDTO.builder()
				.id(c.getId())
				.name(c.getName())
				.description(c.getDescription())
				.price(c.getPrice())
				.stockQuantity(c.getStockQuantity())
				.imageUrl(c.getImageUrl())
				.weight(c.getWeight())
				.releaseDate(c.getReleaseDate())
				.author(c.getAuthor())
				.publisher(c.getPublisher())
				.volumeNumber(c.getVolumeNumber())
				.pages(c.getPages())
				.build();
	}
}
