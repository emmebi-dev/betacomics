package com.betacomics.maps;

import java.util.List;
import com.betacomics.dto.output.BoardGameDTO;
import com.betacomics.models.BoardGame;

public class BoardGameMap {

    public static List<BoardGameDTO> buildBoardGameDTOList(List<BoardGame> lB) {
        if (lB == null) {
            return List.of();
        }
        return lB.stream()
                .map(BoardGameMap::buildBoardGameDTO)
                .toList();
    }
    
    public static BoardGameDTO buildBoardGameDTO(BoardGame b) {
        if (b == null) {
            return null;
        }
        return BoardGameDTO.builder()
                .id(b.getId())
                .name(b.getName())
                .description(b.getDescription())
                .price(b.getPrice())
                .stockQuantity(b.getStockQuantity())
                .imageUrl(b.getImageUrl())
                .weight(b.getWeight())
                .releaseDate(b.getReleaseDate())
                .productType("BOARD_GAME")
                .brand(b.getBrand())
                .minPlayers(b.getMinPlayers())
                .maxPlayers(b.getMaxPlayers())
                .averagePlayTime(b.getAveragePlayTime())
                .recommendedAge(b.getRecommendedAge())
                .build();
    }
}