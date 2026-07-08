package com.betacomics.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.betacomics.dto.output.ProductDTO;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	@Order(1)
	public void listProductTest() throws Exception{
		log.debug("listProductTest");
		
		MvcResult result = mockMvc.perform(get("/product/list"))
	            .andExpect(status().isOk())
	            .andReturn();
		  
		String json = result.getResponse().getContentAsString();
		
		List<ProductDTO> lP = objectMapper.readValue(
	            json,
	            new TypeReference<List<ProductDTO>>() {}
	    );
		
		assertFalse(lP.isEmpty());
		
		lP.forEach(p -> log.debug(p.toString()));
	} 
	
	@Test
	@Order(5)
	public void getById() throws Exception{
		log.debug("getById");
		
		MvcResult result = mockMvc.perform(get("/product/getById/1"))
	            .andExpect(status().isOk())
	            .andReturn();
		  
		String json = result.getResponse().getContentAsString();
		
		ProductDTO p= objectMapper.readValue(json,ProductDTO.class);
		
		log.debug(p.toString());
	} 
}
