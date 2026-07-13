package com.betacomics.dto.output;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "productType",
    visible = true
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = ComicDTO.class, name = "COMIC"),
    @JsonSubTypes.Type(value = ActionFigureDTO.class, name = "ACTION_FIGURE"),
    @JsonSubTypes.Type(value = BoardGameDTO.class, name = "BOARD_GAME")
})
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stockQuantity;
    private String imageUrl;
    private Double weight;
    private LocalDate releaseDate;
    private String productType;
}