package com.betacomics.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.betacomics.dto.input.BoardGameReq;
import com.betacomics.dto.output.BoardGameDTO;

public interface BoardGameService {

    BoardGameDTO create(BoardGameReq req);
    
    BoardGameDTO getById(Long id);
    
    Page<BoardGameDTO> list(Pageable pageable);
    
    BoardGameDTO update(BoardGameReq req);
    
    void delete(Long id);
}