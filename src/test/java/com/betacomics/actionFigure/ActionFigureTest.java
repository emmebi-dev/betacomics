package com.betacomics.actionFigure;

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

import com.betacomics.dto.output.ResponseDTO;
import com.betacomics.dto.input.ActionFigureReq;
import com.betacomics.dto.output.ActionFigureDTO;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ActionFigureTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private static Long Id;
	
	@Test
	@Order(1)
	public void createActionFigureTest() throws Exception{
		log.debug("createActionFigureTest");
		
		ActionFigureReq req = new ActionFigureReq();
		
		req.setName("ma");
        req.setDescription("dffgh");
        req.setPrice(new BigDecimal("49.99"));
        req.setStockQuantity(3);
        req.setImageUrl("dfgbg");
        req.setWeight(100.5);
        req.setReleaseDate(LocalDate.of(2026, 7, 8));
        
        req.setBrand("asddf");
        req.setMaterial("djfgkjd");
        req.setHeight(10.);
        req.setWidth(10.);
        req.setDepth(10.);
        
        
        
		mockMvc.perform(post("/actionFigure/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isOk());
	}
	
	@Test
	@Order(2)
	public void createActionFigureTestError() throws Exception{
		log.debug("createActionFigureTestError");
		ActionFigureReq req = new ActionFigureReq();
		
		MvcResult result =  mockMvc.perform(post("/actionFigure/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				)
		.andExpect(status().isBadRequest())		 
		.andReturn();
		
		String json = result.getResponse().getContentAsString();
		ResponseDTO dto = objectMapper.readValue(json, ResponseDTO.class);
		
		log.debug("ActionFigure create: {}", dto.getMessage());
	}
	
	@Test
	@Order(3)
	public void listActionFigureTest() throws Exception{
		log.debug("listActionFigureTest");
		
		MvcResult result = mockMvc.perform(get("/actionFigure/list"))
	            .andExpect(status().isOk())
	            .andReturn();
		  
		String json = result.getResponse().getContentAsString();
		
		List<ActionFigureDTO> lA = objectMapper.readValue(
	            json,
	            new TypeReference<List<ActionFigureDTO>>() {}
	    );
		
		assertFalse(lA.isEmpty());
		
		lA.forEach(a -> log.debug(a.toString()));
		
		Id = lA.get(0).getId();
	}
	
	@Test
	@Order(4)
	public void updateActionFigureTest() throws Exception{
		log.debug("updateActionFigureTest");

		ActionFigureReq req = new ActionFigureReq();
		req.setId(Id);
		req.setBrand("ciao");

		mockMvc.perform(patch("/actionFigure/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isOk());
	}
	
	@Test
	@Order(5)
	public void getById() throws Exception{
		log.debug("getById");
		
		MvcResult result = mockMvc.perform(get("/actionFigure/getById").param("id", Id.toString()))
	            .andExpect(status().isOk())
	            .andReturn();
		  
		String json = result.getResponse().getContentAsString();
		
		ActionFigureDTO a = objectMapper.readValue(json,ActionFigureDTO.class);
		
		log.debug(a.toString());
	} 
}
