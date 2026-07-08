package com.betacomics.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacomics.dto.input.ActionFigureReq;
import com.betacomics.dto.output.ActionFigureDTO;
import com.betacomics.maps.ActionFigureMap;
import com.betacomics.models.ActionFigure;
import com.betacomics.repositories.ActionFigureRepository;
import com.betacomics.services.interfaces.ActionFigureService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActionFigureServiceImpl implements ActionFigureService {

	private final ActionFigureRepository actionFigureRepository;
	
	@Transactional
	@Override
	public void create(ActionFigureReq req) {
		log.debug("create Action Figure {}", req);
		ActionFigure action = new ActionFigure();
		
		action.setName(req.getName());
		action.setDescription(req.getDescription());
		action.setPrice(req.getPrice());
		action.setStockQuantity(req.getStockQuantity());
		action.setImageUrl(req.getImageUrl());
		action.setWeight(req.getWeight());
		action.setReleaseDate(req.getReleaseDate());
		
		action.setBrand(req.getBrand());
		action.setMaterial(req.getMaterial());
		action.setHeight(req.getHeight());
		action.setWidth(req.getWidth());
		action.setDepth(req.getDepth());
		
		actionFigureRepository.save(action);
	}

	@Override
	public ActionFigureDTO getById(Long id) {
		log.debug("get by id {}", id);
		return ActionFigureMap.buildActionFigureDTO(actionFigureRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found at id: " + id)));
				
	}

	@Override
	public List<ActionFigureDTO> list() {
		log.debug("list");
		return ActionFigureMap.buildActionFigureDTOList(actionFigureRepository.findAll());
	}

	@Transactional
	@Override
	public void update(ActionFigureReq req) {
		log.debug("update Action Figure{}", req);
		
		ActionFigure action = actionFigureRepository.findById(req.getId())
				.orElseThrow(() -> new RuntimeException("Product not found at id: " + req.getId()));
		
		Optional.ofNullable(req.getName()).ifPresent(action::setName);
		Optional.ofNullable(req.getDescription()).ifPresent(action::setDescription);
		Optional.ofNullable(req.getPrice()).ifPresent(action::setPrice);
		Optional.ofNullable(req.getStockQuantity()).ifPresent(action::setStockQuantity);
		Optional.ofNullable(req.getImageUrl()).ifPresent(action::setImageUrl);
		Optional.ofNullable(req.getWeight()).ifPresent(action::setWeight);
		Optional.ofNullable(req.getReleaseDate()).ifPresent(action::setReleaseDate);
		
		Optional.ofNullable(req.getBrand()).ifPresent(action::setBrand);
		Optional.ofNullable(req.getMaterial()).ifPresent(action::setMaterial);
		Optional.ofNullable(req.getHeight()).ifPresent(action::setHeight);
		Optional.ofNullable(req.getWidth()).ifPresent(action::setWidth);
		Optional.ofNullable(req.getDepth()).ifPresent(action::setDepth);
		
		actionFigureRepository.save(action);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		log.debug("delete {}", id);
		actionFigureRepository.deleteById(id);
	}

}
