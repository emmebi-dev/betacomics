package com.betacomics.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.betacomics.dto.input.ActionFigureReq;
import com.betacomics.dto.output.ActionFigureDTO;

public interface ActionFigureService {

    ActionFigureDTO create(ActionFigureReq req);
    
    ActionFigureDTO getById(Long id);
    
    Page<ActionFigureDTO> list(Pageable pageable);
    
    ActionFigureDTO update(ActionFigureReq req);
    
    void delete(Long id);
}