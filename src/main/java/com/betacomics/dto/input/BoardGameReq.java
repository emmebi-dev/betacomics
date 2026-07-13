package com.betacomics.dto.input;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BoardGameReq extends ProductReq {

    @NotBlank(groups = ValidationGroup.Create.class, message = "Board game cannot be created without a valid brand")
    private String brand;
    
    @NotNull(groups = ValidationGroup.Create.class, message = "Board game cannot be created without minimum players")
    @Min(value = 1, groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Minimum players must be at least 1")
    @Max(value = 20, groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Minimum players cannot be greater than 20")
    private Integer minPlayers;
    
    @NotNull(groups = ValidationGroup.Create.class, message = "Board game cannot be created without maximum players")
    @Min(value = 1, groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Maximum players must be at least 1")
    @Max(value = 20, groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Maximum players cannot be greater than 20")
    private Integer maxPlayers;
    
    @Positive(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Average play time must be a positive number")
    @NotNull(groups = ValidationGroup.Create.class, message = "Board game cannot be created without average play time")
    private Integer averagePlayTime;
    
    @PositiveOrZero(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Recommended age must be positive or zero")
    @NotNull(groups = ValidationGroup.Create.class, message = "Board game cannot be created without recommended age")
    private Integer recommendedAge;
}