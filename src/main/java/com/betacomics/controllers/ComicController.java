package com.betacomics.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacomics.dto.input.ComicReq;
import com.betacomics.dto.input.ValidationGroup;
import com.betacomics.dto.output.ComicDTO;
import com.betacomics.dto.output.ResponseDTO;
import com.betacomics.services.interfaces.ComicService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comic")
@Slf4j
public class ComicController {
    
    private final ComicService comicService;
    
    @GetMapping("/list")
    public ResponseEntity<Page<ComicDTO>> list(Pageable pageable) {
        log.debug("REST request to get a page of Comics");
        return ResponseEntity.ok(comicService.list(pageable));
    }
    
    @GetMapping("/getById/{id}")
    public ResponseEntity<ComicDTO> getById(@PathVariable Long id) {
        log.debug("REST request to get Comic : {}", id);
        return ResponseEntity.ok(comicService.getById(id));
    }
    
    // Restituisce 201 Created e l'oggetto appena salvato nel database
    @PostMapping("/create")
    public ResponseEntity<ComicDTO> create(
            @Validated(ValidationGroup.Create.class)
            @RequestBody ComicReq req
    ) {
        log.debug("REST request to save Comic : {}", req);
        ComicDTO createdComic = comicService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComic);
    }
    
    // Restituisce 200 OK e l'oggetto modificato
    @PatchMapping("/update")
    public ResponseEntity<ComicDTO> update(
            @Validated(ValidationGroup.Update.class) 
            @RequestBody ComicReq req
    ) {
        log.debug("REST request to update Comic : {}", req);
        return ResponseEntity.ok(comicService.update(req));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Long id) {
        log.debug("REST request to delete Comic : {}", id);
        comicService.delete(id);
        return ResponseEntity.ok(ResponseDTO.builder()
                    .message("Comic successfully deleted")
                    .build());
    }
}