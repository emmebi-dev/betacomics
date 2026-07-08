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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacomics.dto.input.BoardGameReq;
import com.betacomics.dto.input.ValidationGroup;
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
	public ResponseEntity<Object> list() throws Exception{
		return ResponseEntity.ok(boardGameService.list());
	}
	
	@GetMapping("/getById")
	public ResponseEntity<Object> getById(@RequestParam (required = true) Long id) throws Exception{
		return ResponseEntity.ok(boardGameService.getById(id));
	}
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> create(
			@Validated(ValidationGroup.Create.class)
			@RequestBody (required = true) BoardGameReq req
			) throws Exception{
		boardGameService.create(req);
		return ResponseEntity.ok(ResponseDTO.builder().message("created...").build());
	}
	
	@PatchMapping("/update")
	public ResponseEntity<ResponseDTO> update(
		@RequestBody (required = true) 
		@Validated(ValidationGroup.Update.class) BoardGameReq req
		) throws Exception{
		boardGameService.update(req);
		return ResponseEntity.ok(ResponseDTO.builder()
					.message("updated...")
					.build());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable (required = true) Long id) throws Exception{
		boardGameService.delete(id);
		return ResponseEntity.ok(ResponseDTO.builder()
					.message("deleted...")
					.build());
	}
}
