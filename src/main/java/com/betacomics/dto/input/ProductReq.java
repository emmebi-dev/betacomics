package com.betacomics.dto.input;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductReq {
    
    @NotNull(groups = ValidationGroup.Update.class, message = "Product cannot be updated without a valid id")
    private Long id;
    
    @NotBlank(groups = ValidationGroup.Create.class, message = "Product cannot be created without a valid name")
    @NotBlank(groups = ValidationGroup.Update.class, message = "Product name cannot be blank if provided")
    private String name;
    
    private String description;
    
    @PositiveOrZero(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Price must be positive or zero")
    @NotNull(groups = ValidationGroup.Create.class, message = "Product cannot be created without a valid price")
    private BigDecimal price;
        
    @PositiveOrZero(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Stock quantity must be positive or zero")
    @NotNull(groups = ValidationGroup.Create.class, message = "Product cannot be created without a valid stock quantity")
    private Integer stockQuantity;
        
    private String imageUrl;
        
    @PositiveOrZero(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Weight must be positive or zero")
    @NotNull(groups = ValidationGroup.Create.class, message = "Product cannot be created without a valid weight")
    private Double weight;
        
    private LocalDate releaseDate;
}