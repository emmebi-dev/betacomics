package com.betacomics.delete;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeleteTest {

	 @Autowired
	    private MockMvc mockMvc;

	    @Test
	    @Order(1)
	    public void deleteComicTest() throws Exception {
	        log.debug("deleteComicTest");

	        mockMvc.perform(delete("/comic/delete/1"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.message").exists());
	    }
	    
	    @Test
	    @Order(2)
	    public void deleteBoardGameTest() throws Exception {
	    	log.debug("deleteBoardGameTest");
	    	
	    	mockMvc.perform(delete("/boardGame/delete/2"))
	    	.andExpect(status().isOk())
	    	.andExpect(jsonPath("$.message").exists());
	    }
	    
	    @Test
		@Order(3)
		public void deleteActionFigureTest() throws Exception{
			log.debug("deleteActionFigureTest");
			
			mockMvc.perform(delete("/actionFigure/delete/" +  "3"))
		            .andExpect(status().isOk())
		            .andExpect(jsonPath("$.message").exists());  
		}
	    
	    @Test
	    @Order(4)
	    public void deleteProductTest() throws Exception {
	        log.debug("deleteProductTest");

	        mockMvc.perform(delete("/product/delete/1"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.message").exists());
	    }
}
