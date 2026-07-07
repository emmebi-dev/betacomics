package com.betacomics.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacomics.dto.input.ComicReq;
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
	
	@PostMapping("create")
	public ResponseEntity<ResponseDTO> create(@RequestBody (required = true) ComicReq req) throws Exception{
		comicService.create(req);
		return ResponseEntity.ok(ResponseDTO.builder().message("created...").build());
	}

}
