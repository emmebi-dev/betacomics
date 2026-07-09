package com.betacomics.maps;

import java.util.List;

import com.betacomics.dto.output.UserDTO;
import com.betacomics.models.User;

public class UserMap {
	
	public static List<UserDTO> buildUserDTOList(List<User> lU){
		return lU.stream()
				.map(p -> buildUserDTO(p)
						).toList();
	}
	
	public static UserDTO buildUserDTO(User u) {
		return UserDTO.builder()
				.id(u.getId())
				.username(u.getUsername())
				.email(u.getEmail())
				.password(u.getPassword())
				.isAdmin(u.getIsAdmin())
				.cart(CartMap.buildCartDTO(u.getCart()))
				.orders(OrderMap.buildOrderDTOList(u.getOrders()))
				.build();
	}
}
