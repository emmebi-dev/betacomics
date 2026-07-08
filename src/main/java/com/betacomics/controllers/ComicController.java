package com.betacomics.controllers;

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
	public ResponseEntity<Object> list() throws Exception{
		return ResponseEntity.ok(comicService.list());
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Object> getById(@PathVariable (required = true) Long id) throws Exception{
		return ResponseEntity.ok(comicService.getById(id));
	}
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> create(
			@Validated(ValidationGroup.Create.class)
			@RequestBody (required = true) ComicReq req
			) throws Exception{
		comicService.create(req);
		return ResponseEntity.ok(ResponseDTO.builder().message("created...").build());
	}
	
	@PatchMapping("/update")
	public ResponseEntity<ResponseDTO> update(
		@RequestBody (required = true) 
		@Validated(ValidationGroup.Update.class
				) ComicReq req) throws Exception{
		comicService.update(req);
		return ResponseEntity.ok(ResponseDTO.builder()
					.message("updated...")
					.build());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable (required = true) Long id) throws Exception{
		comicService.delete(id);
		return ResponseEntity.ok(ResponseDTO.builder()
					.message("deleted...")
					.build());
	}

}
