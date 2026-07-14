package com.betacomics.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ActionFigureReq extends ProductReq {

    @NotBlank(groups = ValidationGroup.Create.class, message = "Action figure cannot be created without a valid brand")
    private String brand;
    
    @NotBlank(groups = ValidationGroup.Create.class, message = "Action figure cannot be created without a valid material")
    private String material;
    
    @PositiveOrZero(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Height must be positive or zero")
    @NotNull(groups = ValidationGroup.Create.class, message = "Action figure cannot be created without a valid height")
    private Double height;
        
    @PositiveOrZero(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Width must be positive or zero")
    @NotNull(groups = ValidationGroup.Create.class, message = "Action figure cannot be created without a valid width")
    private Double width;
        
    @PositiveOrZero(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Depth must be positive or zero")
    @NotNull(groups = ValidationGroup.Create.class, message = "Action figure cannot be created without a valid depth")
    private Double depth;
}