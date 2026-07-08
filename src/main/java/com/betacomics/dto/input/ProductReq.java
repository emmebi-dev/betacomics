package com.betacomics.dto.input;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductReq {
	
	@NotNull(groups=ValidationGroup.Update.class, message = "Product cannot be updated without valid id")
    private Long id;
	
	@NotBlank(groups=ValidationGroup.Create.class, message = "Product cannot be created without valid name")
    private String name;
	
    private String description;
	
    @PositiveOrZero(groups= {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Product cannot be created or updated without valid price")
    @NotNull(groups= {ValidationGroup.Create.class}, message = "Product cannot be created without valid price")
    private BigDecimal price;
    
    @PositiveOrZero(groups= {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Product cannot be created or updated without valid stock quantity")
    @NotNull(groups= {ValidationGroup.Create.class}, message = "Product cannot be created without valid stock quantity")
    private Integer stockQuantity;
    
    private String imageUrl;
    
    @PositiveOrZero(groups= {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Product cannot be created or updated without valid weight")
    @NotNull(groups= {ValidationGroup.Create.class}, message = "Product cannot be created without valid weight")
    private Double weight;
    
    
    private LocalDate releaseDate;
}
