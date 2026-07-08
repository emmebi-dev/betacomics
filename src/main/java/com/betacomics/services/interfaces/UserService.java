package com.betacomics.services.interfaces;

import java.util.List;

import com.betacomics.dto.input.UserReq;
import com.betacomics.dto.output.UserDTO;

public interface UserService {
	
	void create(UserReq req);
	
	UserDTO getById(Long id);
	
	List<UserDTO> list();
	
	void update(UserReq req);
	
	void delete(Long id);
}
