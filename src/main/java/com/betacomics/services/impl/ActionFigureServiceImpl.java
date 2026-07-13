package com.betacomics.services.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacomics.dto.input.ActionFigureReq;
import com.betacomics.dto.output.ActionFigureDTO;
import com.betacomics.maps.ActionFigureMap;
import com.betacomics.models.ActionFigure;
import com.betacomics.repositories.ActionFigureRepository;
import com.betacomics.services.interfaces.ActionFigureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActionFigureServiceImpl implements ActionFigureService {

    private final ActionFigureRepository actionFigureRepository;
    
    @Transactional
    @Override
    public ActionFigureDTO create(ActionFigureReq req) {
        log.debug("create Action Figure: {}", req);
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
        
        ActionFigure savedAction = actionFigureRepository.save(action);
        return ActionFigureMap.buildActionFigureDTO(savedAction);
    }

    @Override
    @Transactional(readOnly = true)
    public ActionFigureDTO getById(Long id) {
        log.debug("get action figure by id: {}", id);
        return actionFigureRepository.findById(id)
                .map(ActionFigureMap::buildActionFigureDTO)
                .orElseThrow(() -> new RuntimeException("Action Figure not found at id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ActionFigureDTO> list(Pageable pageable) {
        log.debug("list action figures paginated");
        return actionFigureRepository.findAll(pageable).map(ActionFigureMap::buildActionFigureDTO);
    }

    @Transactional
    @Override
    public ActionFigureDTO update(ActionFigureReq req) {
        log.debug("update Action Figure: {}", req);
        
        ActionFigure action = actionFigureRepository.findById(req.getId())
                .orElseThrow(() -> new RuntimeException("Action Figure not found at id: " + req.getId()));
        
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
        
        ActionFigure updatedAction = actionFigureRepository.save(action);
        return ActionFigureMap.buildActionFigureDTO(updatedAction);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        log.debug("delete action figure: {}", id);
        if (!actionFigureRepository.existsById(id)) {
            throw new RuntimeException("Action Figure not found at id: " + id);
        }
        actionFigureRepository.deleteById(id);
    }
}