package com.betacomics.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BoardGameDTO extends ProductDTO{

	private String brand;
	private Integer minPlayers;
    private Integer maxPlayers;
    private Integer averagePlayTime;
    private Integer recommendedAge;
}
