package com.betacomics.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacomics.dto.input.ComicReq;
import com.betacomics.dto.output.ComicDTO;
import com.betacomics.maps.ComicMap;
import com.betacomics.models.Comic;
import com.betacomics.repositories.ComicRepository;
import com.betacomics.services.interfaces.ComicService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ComicServiceImpl implements ComicService{

	private final ComicRepository comicRepository;
	
	@Transactional
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
		log.debug("get by id {}", id);
		return ComicMap.buildComicDTO(comicRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found at id: " + id)));
	}

	@Override
	public List<ComicDTO> list() {
		log.debug("list");
		return ComicMap.buildComicDTOList(comicRepository.findAll());
	}

	@Transactional
	@Override
	public void update(ComicReq req) {
		log.debug("update comic {}", req);
		
		Comic comic = comicRepository.findById(req.getId())
				.orElseThrow(() -> new RuntimeException("Product not found at id: " + req.getId()));
		
		Optional.ofNullable(req.getName()).ifPresent(comic::setName);
		Optional.ofNullable(req.getDescription()).ifPresent(comic::setDescription);
		Optional.ofNullable(req.getPrice()).ifPresent(comic::setPrice);
		Optional.ofNullable(req.getStockQuantity()).ifPresent(comic::setStockQuantity);
		Optional.ofNullable(req.getImageUrl()).ifPresent(comic::setImageUrl);
		Optional.ofNullable(req.getWeight()).ifPresent(comic::setWeight);
		Optional.ofNullable(req.getReleaseDate()).ifPresent(comic::setReleaseDate);
		
		Optional.ofNullable(req.getAuthor()).ifPresent(comic::setAuthor);
		Optional.ofNullable(req.getPublisher()).ifPresent(comic::setPublisher);
		Optional.ofNullable(req.getVolumeNumber()).ifPresent(comic::setVolumeNumber);
		Optional.ofNullable(req.getPages()).ifPresent(comic::setPages);
		
		comicRepository.save(comic);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		log.debug("delete {}", id);
		comicRepository.deleteById(id);
	}

}
