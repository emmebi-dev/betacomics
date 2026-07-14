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
public class ComicReq extends ProductReq {
    
    @NotBlank(groups = ValidationGroup.Create.class, message = "Comic cannot be created without a valid author")
    private String author;
    
    @NotBlank(groups = ValidationGroup.Create.class, message = "Comic cannot be created without a valid publisher")
    private String publisher;
    
    @PositiveOrZero(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Volume number must be positive or zero")
    private Integer volumeNumber;
    
    @PositiveOrZero(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Pages must be positive or zero")
    @NotNull(groups = ValidationGroup.Create.class, message = "Comic cannot be created without a valid pages count")
    private Integer pages;
}