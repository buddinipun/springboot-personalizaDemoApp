package com.smtaps.demoApi;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smtaps.demoApi.DTO.ShopperDTO;
import com.smtaps.demoApi.controller.ShopperController;
import com.smtaps.demoApi.service.ShopperService;

@SpringBootTest
@AutoConfigureMockMvc
public class ShopperControllerTest {

	 @Mock
	    private ShopperService shopperService;

	    @InjectMocks
	    private ShopperController shopperController;

	    private final ObjectMapper objectMapper = new ObjectMapper();

	    private MockMvc mockMvc;

	    @Test
	    @WithMockUser(roles = "client_internal")
	    public void testSaveShopperDetails_Success() throws Exception {
	        // Arrange
	        ShopperDTO shopperDTO = new ShopperDTO(); // Create a sample ShopperDTO
	        String requestBody = objectMapper.writeValueAsString(shopperDTO);
	        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Shopper details saved successfully", HttpStatus.CREATED);
	        
	        // Mock behavior of shopperService
	       // when(shopperService.saveShopperDetails(any(ShopperDTO.class))).thenReturn(expectedResponse);
	        
	        // Act & Assert
	        mockMvc.perform(post("/api/v1/internal/shoppers")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(requestBody))
	                .andExpect(status().isCreated());

	        // Verify that shopperService.saveShopperDetails was called
	        verify(shopperService).saveShopperDetails(shopperDTO);
	    }

	    @Test
	    @WithMockUser(roles = "client_internal")
	    public void testSaveShopperDetails_InternalServerError() throws Exception {
	        // Arrange
	        ShopperDTO shopperDTO = new ShopperDTO(); // Create a sample ShopperDTO
	        String requestBody = objectMapper.writeValueAsString(shopperDTO);
	        String errorMessage = "Internal Server Error";
	        
	        // Mock behavior of shopperService to throw an exception
	        doThrow(new RuntimeException(errorMessage)).when(shopperService).saveShopperDetails(any(ShopperDTO.class));
	        
	        // Act & Assert
	        mockMvc.perform(post("/api/v1/internal/shoppers")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(requestBody))
	                .andExpect(status().isInternalServerError());

	        // Verify that shopperService.saveShopperDetails was called
	        verify(shopperService).saveShopperDetails(shopperDTO);
	    }

	    // Initialize MockMvc for each test method
	    @BeforeEach
	    public void setUp() {
	        mockMvc = MockMvcBuilders.standaloneSetup(shopperController).build();
	    }
	}