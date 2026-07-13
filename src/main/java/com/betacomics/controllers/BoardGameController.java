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

import com.betacomics.dto.input.BoardGameReq;
import com.betacomics.dto.input.ValidationGroup;
import com.betacomics.dto.output.BoardGameDTO;
import com.betacomics.dto.output.ResponseDTO;
import com.betacomics.services.interfaces.BoardGameService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boardGame")
@Slf4j
public class BoardGameController {

    private final BoardGameService boardGameService;
    
    @GetMapping("/list")
    public ResponseEntity<Page<BoardGameDTO>> list(Pageable pageable) {
        log.debug("REST request to get a page of Board Games");
        return ResponseEntity.ok(boardGameService.list(pageable));
    }
    
    @GetMapping("/getById/{id}")
    public ResponseEntity<BoardGameDTO> getById(@PathVariable Long id) {
        log.debug("REST request to get Board Game : {}", id);
        return ResponseEntity.ok(boardGameService.getById(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<BoardGameDTO> create(
            @Validated(ValidationGroup.Create.class)
            @RequestBody BoardGameReq req
    ) {
        log.debug("REST request to save Board Game : {}", req);
        BoardGameDTO createdGame = boardGameService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
    }
    
    @PatchMapping("/update")
    public ResponseEntity<BoardGameDTO> update(
            @Validated(ValidationGroup.Update.class) 
            @RequestBody BoardGameReq req
    ) {
        log.debug("REST request to update Board Game : {}", req);
        return ResponseEntity.ok(boardGameService.update(req));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Long id) {
        log.debug("REST request to delete Board Game : {}", id);
        boardGameService.delete(id);
        return ResponseEntity.ok(ResponseDTO.builder()
                    .message("Board Game successfully deleted")
                    .build());
    }
}