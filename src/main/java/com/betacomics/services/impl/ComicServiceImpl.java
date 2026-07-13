package com.betacomics.services.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ComicServiceImpl implements ComicService {

    private final ComicRepository comicRepository;
    
    @Transactional
    @Override
    public ComicDTO create(ComicReq req) {
        log.debug("create comic: {}", req);
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
        
        Comic savedComic = comicRepository.save(comic);
        return ComicMap.buildComicDTO(savedComic);
    }

    @Override
    @Transactional(readOnly = true)
    public ComicDTO getById(Long id) {
        log.debug("get comic by id: {}", id);
        return comicRepository.findById(id)
                .map(ComicMap::buildComicDTO)
                .orElseThrow(() -> new RuntimeException("Comic not found at id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ComicDTO> list(Pageable pageable) {
        log.debug("list comics paginated");
        return comicRepository.findAll(pageable).map(ComicMap::buildComicDTO);
    }

    @Transactional
    @Override
    public ComicDTO update(ComicReq req) {
        log.debug("update comic: {}", req);
        
        Comic comic = comicRepository.findById(req.getId())
                .orElseThrow(() -> new RuntimeException("Comic not found at id: " + req.getId()));
        
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
        
        Comic updatedComic = comicRepository.save(comic);
        return ComicMap.buildComicDTO(updatedComic);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        log.debug("delete comic: {}", id);
        if (!comicRepository.existsById(id)) {
            throw new RuntimeException("Comic not found at id: " + id);
        }
        comicRepository.deleteById(id);
    }
}