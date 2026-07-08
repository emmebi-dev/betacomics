package com.betacomics.comic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.betacomics.dto.input.ComicReq;
import com.betacomics.dto.output.ComicDTO;
import com.betacomics.dto.output.ResponseDTO;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ComicTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private static Long Id;
	
	@Test
	@Order(1)
	public void createComicTest() throws Exception{
		log.debug("createComicTest");
		
		ComicReq req = new ComicReq();
		
		req.setName("ma");
        req.setDescription("dffgh");
        req.setPrice(new BigDecimal("49.99"));
        req.setStockQuantity(3);
        req.setImageUrl("dfgbg");
        req.setWeight(100.5);
        req.setReleaseDate(LocalDate.of(2026, 7, 8));
        
        req.setAuthor("ad");
        req.setPublisher("dfg");
        req.setVolumeNumber(2);
        req.setPages(100);
        
		mockMvc.perform(post("/comic/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isOk());
	}
	
	@Test
	@Order(2)
	public void createComicTestError() throws Exception{
		log.debug("createComicTestError");
		ComicReq req = new ComicReq();
		
		MvcResult result =  mockMvc.perform(post("/comic/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				)
		.andExpect(status().isBadRequest())		 
		.andReturn();
		
		String json = result.getResponse().getContentAsString();
		ResponseDTO dto = objectMapper.readValue(json, ResponseDTO.class);
		
		log.debug("Comic create: {}", dto.getMessage());
	}
	
	@Test
	@Order(3)
	public void listComicTest() throws Exception{
		log.debug("listComicTest");
		
		MvcResult result = mockMvc.perform(get("/comic/list"))
	            .andExpect(status().isOk())
	            .andReturn();
		  
		String json = result.getResponse().getContentAsString();
		
		List<ComicDTO> lC = objectMapper.readValue(
	            json,
	            new TypeReference<List<ComicDTO>>() {}
	    );
		
		assertFalse(lC.isEmpty());
		
		lC.forEach(c -> log.debug(c.toString()));
		
		Id = lC.get(0).getId();
	}
	
	@Test
	@Order(4)
	public void updateComicTest() throws Exception{
		log.debug("updateComicTest");

		ComicReq req = new ComicReq();
		req.setId(Id);
		req.setAuthor("ba");

		mockMvc.perform(patch("/comic/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isOk());
	}
	
	@Test
	@Order(5)
	public void getById() throws Exception{
		log.debug("getById");
		
		MvcResult result = mockMvc.perform(get("/comic/getById").param("id", Id.toString()))
	            .andExpect(status().isOk())
	            .andReturn();
		  
		String json = result.getResponse().getContentAsString();
		
		ComicDTO a = objectMapper.readValue(json,ComicDTO.class);
		
		log.debug(a.toString());
	} 
	
}
