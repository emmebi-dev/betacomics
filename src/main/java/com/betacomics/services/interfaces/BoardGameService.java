package com.betacomics.services.interfaces;

import java.util.List;

import com.betacomics.dto.input.BoardGameReq;
import com.betacomics.dto.output.BoardGameDTO;

public interface BoardGameService {

	public void create(BoardGameReq req);
	
	public BoardGameDTO getById(Long id);
	public List<BoardGameDTO> list();
	
	public void update(BoardGameReq req);
	
	public void delete(Long id);
}
