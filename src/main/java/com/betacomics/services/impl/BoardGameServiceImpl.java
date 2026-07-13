package com.betacomics.services.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacomics.dto.input.BoardGameReq;
import com.betacomics.dto.output.BoardGameDTO;
import com.betacomics.maps.BoardGameMap;
import com.betacomics.models.BoardGame;
import com.betacomics.repositories.BoardGameRepository;
import com.betacomics.services.interfaces.BoardGameService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardGameServiceImpl implements BoardGameService {

    private final BoardGameRepository boardGameRepository;
    
    @Transactional
    @Override
    public BoardGameDTO create(BoardGameReq req) {
        log.debug("create Board Game: {}", req);
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
        
        if (board.getMinPlayers() > board.getMaxPlayers()) {
            throw new RuntimeException("Minimum players cannot be greater than maximum players");
        }
        
        board.setAveragePlayTime(req.getAveragePlayTime());
        board.setRecommendedAge(req.getRecommendedAge());
        
        BoardGame savedBoard = boardGameRepository.save(board);
        return BoardGameMap.buildBoardGameDTO(savedBoard);
    }

    @Override
    @Transactional(readOnly = true)
    public BoardGameDTO getById(Long id) {
        log.debug("get board game by id: {}", id);
        return boardGameRepository.findById(id)
                .map(BoardGameMap::buildBoardGameDTO)
                .orElseThrow(() -> new RuntimeException("Board Game not found at id: " + id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<BoardGameDTO> list(Pageable pageable) {
        log.debug("list board games paginated");
        return boardGameRepository.findAll(pageable).map(BoardGameMap::buildBoardGameDTO);
    }

    @Transactional
    @Override
    public BoardGameDTO update(BoardGameReq req) {
        log.debug("update Board Game: {}", req);
        
        BoardGame board = boardGameRepository.findById(req.getId())
                .orElseThrow(() -> new RuntimeException("Board Game not found at id: " + req.getId()));
        
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
        
        // CORRETTO: Validazione cross-campo anti-bug unendo dati Req e dati DB
        if (board.getMinPlayers() > board.getMaxPlayers()) {
            throw new RuntimeException("Minimum players cannot be greater than maximum players after update");
        }
        
        BoardGame updatedBoard = boardGameRepository.save(board);
        return BoardGameMap.buildBoardGameDTO(updatedBoard);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        log.debug("delete board game: {}", id);
        if (!boardGameRepository.existsById(id)) {
            throw new RuntimeException("Board Game not found at id: " + id);
        }
        boardGameRepository.deleteById(id);
    }
}