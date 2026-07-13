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

import com.betacomics.dto.input.ActionFigureReq;
import com.betacomics.dto.input.ValidationGroup;
import com.betacomics.dto.output.ActionFigureDTO;
import com.betacomics.dto.output.ResponseDTO;
import com.betacomics.services.interfaces.ActionFigureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/actionFigure")
@Slf4j
public class ActionFigureController {

    private final ActionFigureService actionFigureService;
    
    @GetMapping("/list")
    public ResponseEntity<Page<ActionFigureDTO>> list(Pageable pageable) {
        log.debug("REST request to get a page of Action Figures");
        return ResponseEntity.ok(actionFigureService.list(pageable));
    }
    
    @GetMapping("/getById/{id}")
    public ResponseEntity<ActionFigureDTO> getById(@PathVariable Long id) {
        log.debug("REST request to get Action Figure : {}", id);
        return ResponseEntity.ok(actionFigureService.getById(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<ActionFigureDTO> create(
            @Validated(ValidationGroup.Create.class)
            @RequestBody ActionFigureReq req
    ) {
        log.debug("REST request to save Action Figure : {}", req);
        ActionFigureDTO createdFigure = actionFigureService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFigure);
    }
    
    @PatchMapping("/update")
    public ResponseEntity<ActionFigureDTO> update(
            @Validated(ValidationGroup.Update.class) 
            @RequestBody ActionFigureReq req
    ) {
        log.debug("REST request to update Action Figure : {}", req);
        return ResponseEntity.ok(actionFigureService.update(req));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Long id) {
        log.debug("REST request to delete Action Figure : {}", id);
        actionFigureService.delete(id);
        return ResponseEntity.ok(ResponseDTO.builder()
                    .message("Action Figure successfully deleted")
                    .build());
    }
}