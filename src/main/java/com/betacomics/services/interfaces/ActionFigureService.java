package com.betacomics.services.interfaces;

import java.util.List;

import com.betacomics.dto.input.ActionFigureReq;
import com.betacomics.dto.output.ActionFigureDTO;

public interface ActionFigureService {

	public void create(ActionFigureReq req);
	
	public ActionFigureDTO getById(Long id);
	public List<ActionFigureDTO> list();
	
	public void update(ActionFigureReq req);
	
	public void delete(Long id);
	
}
