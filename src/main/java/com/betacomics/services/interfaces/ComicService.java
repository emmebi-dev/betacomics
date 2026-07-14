package com.betacomics.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.betacomics.dto.input.ComicReq;
import com.betacomics.dto.output.ComicDTO;

public interface ComicService {
    ComicDTO create(ComicReq req);
    
    ComicDTO getById(Long id);
    
    Page<ComicDTO> list(Pageable pageable);
    
    ComicDTO update(ComicReq req);
    
    void delete(Long id);
}