package com.betacomics.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacomics.dto.input.BoardGameReq;
import com.betacomics.dto.output.BoardGameDTO;
import com.betacomics.maps.BoardGameMap;
import com.betacomics.models.BoardGame;
import com.betacomics.repositories.BoardGameRepository;
import com.betacomics.services.interfaces.BoardGameService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardGameServiceImpl implements BoardGameService {

	private final BoardGameRepository boardGameRepository;
	
	@Transactional
	@Override
	public void create(BoardGameReq req) {
		log.debug("create Board Game {}", req);
		BoardGame board = new BoardGame();
		
		board.setName(req.getName());
		board.setDescription(req.getDescription());
		board.setPrice(req.getPrice());
		board.setStockQuantity(req.getStockQuantity());
		board.setImageUrl(req.getImageUrl());
		board.setWeight(req.getWeight());
		board.setReleaseDate(req.getReleaseDate());
		
		board.setBrand(req.getBrand());
		board.setMinPlayers(req.getMinPlayers());
		board.setMaxPlayers(req.getMaxPlayers());
		
		if (board.getMinPlayers() != null && board.getMaxPlayers() != null) {
		    if (board.getMinPlayers() > board.getMaxPlayers()) {
		        throw new RuntimeException("Minimum players cannot be greater than maximum players");
		    }
		}
		board.setAveragePlayTime(req.getAveragePlayTime());
		board.setRecommendedAge(req.getRecommendedAge());
		
		boardGameRepository.save(board);
	}

	@Override
	public BoardGameDTO getById(Long id) {
		log.debug("get by id {}", id);
		return BoardGameMap.buildBoardGameDTO(boardGameRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found at id: " + id)));
	}
	
	@Override
	public List<BoardGameDTO> list() {
		log.debug("list");
		return BoardGameMap.buildBoardGameDTOList(boardGameRepository.findAll());
	}

	@Transactional
	@Override
	public void update(BoardGameReq req) {
		log.debug("update Board Game {}", req);
		
		BoardGame board = boardGameRepository.findById(req.getId())
				.orElseThrow(() -> new RuntimeException("Product not found at id: " + req.getId()));
		
		Optional.ofNullable(req.getName()).ifPresent(board::setName);
		Optional.ofNullable(req.getDescription()).ifPresent(board::setDescription);
		Optional.ofNullable(req.getPrice()).ifPresent(board::setPrice);
		Optional.ofNullable(req.getStockQuantity()).ifPresent(board::setStockQuantity);
		Optional.ofNullable(req.getImageUrl()).ifPresent(board::setImageUrl);
		Optional.ofNullable(req.getWeight()).ifPresent(board::setWeight);
		Optional.ofNullable(req.getReleaseDate()).ifPresent(board::setReleaseDate);
		
		Optional.ofNullable(req.getBrand()).ifPresent(board::setBrand);
		Optional.ofNullable(req.getMinPlayers()).ifPresent(board::setMinPlayers);
		Optional.ofNullable(req.getMaxPlayers()).ifPresent(board::setMaxPlayers);
		Optional.ofNullable(req.getAveragePlayTime()).ifPresent(board::setAveragePlayTime);
		Optional.ofNullable(req.getRecommendedAge()).ifPresent(board::setRecommendedAge);
		
		boardGameRepository.save(board);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		log.debug("delete {}", id);
		boardGameRepository.deleteById(id);
	}
}
