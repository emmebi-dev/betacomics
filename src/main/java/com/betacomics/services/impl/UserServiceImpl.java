package com.betacomics.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacomics.dto.input.UserReq;
import com.betacomics.dto.output.UserDTO;
import com.betacomics.maps.CartMap;
import com.betacomics.maps.UserMap;
import com.betacomics.models.User;
import com.betacomics.repositories.UserRepository;
import com.betacomics.services.interfaces.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Transactional
	@Override
	public void create(UserReq req) {
		log.debug("create user {}", req);
		User user = new User();

		user.setUsername(req.getUsername());
		user.setEmail(req.getEmail());
		user.setPassword(req.getPassword());
		user.setIsAdmin(req.getIsAdmin());
		
		try {
			user.setCart(CartMap.toEntity(req.getCart(), user));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//user.setOrders(null);

		userRepository.save(user);
	}

	@Override
	public UserDTO getById(Long id) {
		log.debug("get by id {}", id);
		return UserMap.buildUserDTO(
				userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found at id: " + id)));
	}

	@Override
	public List<UserDTO> list() {
		log.debug("list");
		return UserMap.buildUserDTOList(userRepository.findAll());
	}

	@Transactional
	@Override
	public void update(UserReq req) {
		log.debug("update user {}", req);

		User user = userRepository.findById(req.getId())
				.orElseThrow(() -> new RuntimeException("User not found at id: " + req.getId()));

		Optional.ofNullable(req.getUsername()).ifPresent(user::setUsername);
		Optional.ofNullable(req.getEmail()).ifPresent(user::setEmail);
		Optional.ofNullable(req.getPassword()).ifPresent(user::setPassword);
		Optional.ofNullable(req.getIsAdmin()).ifPresent(user::setIsAdmin);
		
		try {
			Optional.ofNullable(CartMap.toEntity(req.getCart(), user)).ifPresent(user::setCart);
		} catch (Exception e) {
			e.printStackTrace();
		}

		userRepository.save(user);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		log.debug("delete {}", id);
		
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found for delete at id: " + id));
		
		userRepository.delete(user);
	}

}
