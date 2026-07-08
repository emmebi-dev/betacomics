package com.betacomics.dto.input;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardGameReq extends ProductReq{

	@NotNull(groups=ValidationGroup.Create.class, message = "Board game cannot be created without valid brand")
	private String brand;
	
	@NotNull(
		    groups = ValidationGroup.Create.class,
		    message = "Board game cannot be created without valid min players"
		)
	@Min(
		    value = 1,
		    groups = {ValidationGroup.Create.class, ValidationGroup.Update.class},
		    message = "Minimum players must be at least 1"
		)
	@Max(
		    value = 20,
		    groups = {ValidationGroup.Create.class, ValidationGroup.Update.class},
		    message = "Minimum players cannot be greater than 20"
		)
	private Integer minPlayers;
	
	@NotNull(
		    groups = ValidationGroup.Create.class,
		    message = "Board game cannot be created without valid max players"
		)
	@Min(
		    value = 1,
		    groups = {ValidationGroup.Create.class, ValidationGroup.Update.class},
		    message = "Maximum players must be at least 1"
		)
	@Max(
		    value = 20,
		    groups = {ValidationGroup.Create.class, ValidationGroup.Update.class},
		    message = "Maximum players cannot be greater than 20"
		)
	private Integer maxPlayers;
	
	@Positive(groups= {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Board game cannot be created or updated without valid average play time")
    @NotNull(groups=ValidationGroup.Create.class, message = "Board game cannot be created without valid average play time")
    private Integer averagePlayTime;
	
	@Positive(groups= {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Board game cannot be created or updated without valid recommended age")
    @NotNull(groups=ValidationGroup.Create.class, message = "Board game cannot be created without valid recommended age")
    private Integer recommendedAge;
}
