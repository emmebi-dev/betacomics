package com.betacomics.maps;

import java.util.List;

import com.betacomics.dto.output.ActionFigureDTO;
import com.betacomics.models.ActionFigure;

public class ActionFigureMap {

	public static List<ActionFigureDTO> buildActionFigureDTOList(List<ActionFigure> lA){
		return lA.stream()
				.map(a -> buildActionFigureDTO(a)
						).toList();
	};
	
	public static ActionFigureDTO buildActionFigureDTO(ActionFigure a) {
		return ActionFigureDTO.builder()
				.id(a.getId())
				.name(a.getName())
				.description(a.getDescription())
				.price(a.getPrice())
				.stockQuantity(a.getStockQuantity())
				.imageUrl(a.getImageUrl())
				.weight(a.getWeight())
				.releaseDate(a.getReleaseDate())
				.brand(a.getBrand())
				.material(a.getMaterial())
				.height(a.getHeight())
				.width(a.getWidth())
				.depth(a.getDepth())
				.build();
	}
}
