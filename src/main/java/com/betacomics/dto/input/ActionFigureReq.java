package com.betacomics.dto.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ActionFigureReq extends ProductReq{

	@NotNull(groups=ValidationGroup.Create.class, message = "Action figure cannot be created without valid brand")
	private String brand;
	
	@NotNull(groups=ValidationGroup.Create.class, message = "Action figure cannot be created without valid material")
    private String material;
	
	@PositiveOrZero(groups= {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Action figure cannot be created or updated without valid height")
    @NotNull(groups=ValidationGroup.Create.class, message = "Action figure cannot be created without valid height")
    private Double height;
    
	@PositiveOrZero(groups= {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Action figure cannot be created or updated without valid width")
    @NotNull(groups=ValidationGroup.Create.class, message = "Action figure cannot be created without valid width")
    private Double width;
    
	@PositiveOrZero(groups= {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Action figure cannot be created or updated without valid depth")
    @NotNull(groups=ValidationGroup.Create.class, message = "Action figure cannot be created without valid depth")
    private Double depth;
}
