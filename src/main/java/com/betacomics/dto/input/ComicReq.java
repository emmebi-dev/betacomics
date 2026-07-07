package com.betacomics.dto.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ComicReq extends ProductReq{
	
	@NotNull(groups=ValidationGroup.create.class, message = "Comic cannot be created without valid author")
	private String author;
	
	@NotNull(groups=ValidationGroup.create.class, message = "Comic cannot be created without valid publisher")
    private String publisher;
	
	@PositiveOrZero(groups= {ValidationGroup.create.class, ValidationGroup.update.class}, message = "Comic cannot be created or updated without valid volume number")
    private Integer volumeNumber;
	
	@PositiveOrZero(groups= {ValidationGroup.create.class, ValidationGroup.update.class}, message = "Comic cannot be created or updated without valid pages")
    @NotNull(groups=ValidationGroup.create.class, message = "Comic cannot be created without valid pages")
    private Integer pages;
}
