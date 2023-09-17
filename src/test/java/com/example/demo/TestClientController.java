//package com.example.demo;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.example.demo.controller.ClientController;
//
//@WebMvcTest(ClientController.class)
//public class TestClientController {
//	
//	@Autowired
//	private MockMvc mvc;
//				
//		@Test
//		public void getAllClient() throws Exception {
//			mvc.perform(MockMvcRequestBuilders
//					.get("/clients")
//					.accept(MediaType.APPLICATION_JSON)
//					.andDO(print())
//					.andExpect(status().isOk())								
//					);	
//		}
//	
//}
