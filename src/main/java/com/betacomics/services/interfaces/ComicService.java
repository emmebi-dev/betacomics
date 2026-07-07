package com.betacomics.services.interfaces;

import java.util.List;

import com.betacomics.dto.input.ComicReq;
import com.betacomics.dto.output.ComicDTO;

public interface ComicService {
	public void create(ComicReq req);
	
	public ComicDTO getById(Long id);
	public List<ComicDTO> list();
	
	public void update(ComicReq req);
	
	public void delete(Long id);
}
