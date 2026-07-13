package com.betacomics.maps;

import java.util.List;
import com.betacomics.dto.output.ActionFigureDTO;
import com.betacomics.models.ActionFigure;

public class ActionFigureMap {

    public static List<ActionFigureDTO> buildActionFigureDTOList(List<ActionFigure> lA) {
        if (lA == null) {
            return List.of();
        }
        return lA.stream()
                .map(ActionFigureMap::buildActionFigureDTO)
                .toList();
    }
    
    public static ActionFigureDTO buildActionFigureDTO(ActionFigure a) {
        if (a == null) {
            return null;
        }
        return ActionFigureDTO.builder()
                .id(a.getId())
                .name(a.getName())
                .description(a.getDescription())
                .price(a.getPrice())
                .stockQuantity(a.getStockQuantity())
                .imageUrl(a.getImageUrl())
                .weight(a.getWeight())
                .releaseDate(a.getReleaseDate())
                .productType("ACTION_FIGURE") // CORRETTO: Diciamo a Jackson e al FE che questa è una figure
                .brand(a.getBrand())
                .material(a.getMaterial())
                .height(a.getHeight())
                .width(a.getWidth())
                .depth(a.getDepth())
                .build();
    }
}