package com.betacomics.dto.input;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserReq {

	@NotNull(groups = ValidationGroup.Update.class, message = "User cannot be updated without valid id")
	private Long id;

	@NotBlank(groups = ValidationGroup.Create.class, message = "User cannot be created without valid username")
	private String username;

	@NotBlank(groups = ValidationGroup.Create.class, message = "User cannot be created without valid email")
	private String email;

	@NotBlank(groups = ValidationGroup.Create.class, message = "User cannot be created without valid password")
	private String password;

	@NotNull(groups = ValidationGroup.Create.class, message = "User cannot be updated without valid isAdmin")
	private Boolean isAdmin;
	
	@NotNull(groups = ValidationGroup.Create.class, message = "User cannot be created without valid Cart")
	private CartReq cart;
	
	@NotNull(groups = ValidationGroup.Create.class, message = "User cannot be updated without valid orders")
	private List<OrderReq> orders;
}
