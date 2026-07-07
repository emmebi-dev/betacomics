package com.betacomics.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacomics.dto.input.ComicReq;
import com.betacomics.dto.output.ComicDTO;
import com.betacomics.maps.ComicMap;
import com.betacomics.models.Comic;
import com.betacomics.repositories.ComicRepository;
import com.betacomics.services.interfaces.ComicService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ComicServiceImpl implements ComicService{

	private final ComicRepository comicRepository;
	
	@Override
	public void create(ComicReq req) {
		log.debug("create comic {}", req);
		Comic comic = new Comic();
		
		comic.setName(req.getName());
		comic.setDescription(req.getDescription());
		comic.setPrice(req.getPrice());
		comic.setStockQuantity(req.getStockQuantity());
		comic.setImageUrl(req.getImageUrl());
		comic.setWeight(req.getWeight());
		comic.setReleaseDate(req.getReleaseDate());
		
		comic.setAuthor(req.getAuthor());
		comic.setPublisher(req.getPublisher());
		comic.setVolumeNumber(req.getVolumeNumber());
		comic.setPages(req.getPages());
		
		comicRepository.save(comic);
	}

	@Override
	public ComicDTO getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ComicDTO> list() {
		log.debug("list");
		return ComicMap.buildComicDTOList(comicRepository.findAll());
	}

	@Override
	public void update(ComicReq req) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
