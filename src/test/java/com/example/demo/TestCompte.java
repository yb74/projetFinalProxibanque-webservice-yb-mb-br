package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dto.CompteCourantDTO;
import com.example.demo.mapper.CompteCourantMapper;
import com.example.demo.model.Carte;
import com.example.demo.model.Client;
import com.example.demo.model.CompteCourant;
import com.example.demo.repository.CompteCourantRepository;
import com.example.demo.service.CompteCourantServiceImpl;

public class TestCompte {
	
//	@InjectMocks
//	private CompteCourantServiceImpl compteCourantService;
//	
//	@Mock
//	private CompteCourantRepository compteRepository;
//	
//	@Mock
//	private CompteCourantMapper compteCourantMapper;
//
//
//	@BeforeEach
//	public void setUp() {
//		MockitoAnnotations.openMocks(this);
//	}
//	
//	@Test	
//	public void testGetAllCompte() {
//		List<CompteCourant> mockCompteCourant = new ArrayList<>();
//		mockCompteCourant.add(new CompteCourant(1500.2,55.7,new Carte(),new Client()));
//		mockCompteCourant.add(new CompteCourant(3500.4,70.3,new Carte(),new Client()));
	
	
//		List<CompteCourantDTO> mockCompteCourantsDTO = new ArrayList<>();
//		mockCompteCourantsDTO.add(new CompteCourantDTO(1L, 4500, 62.4,new Carte(),new Client()));
//		mockCompteCourantsDTO.add(new CompteCourantDTO(2L, 1500, 20.2,new Carte(),new Client()));
//		
//		when(compteRepository.findAll()).thenReturn(mockCompteCourant);
//		
//		  when(compteCourantMapper.toDto(any(CompteCourant.class)))
//          .thenAnswer(e -> {
//              // Ici,  mapper le compteCourant à son DTO correspondant et le retourner
//          	CompteCourant compteCourant = e.getArgument(0);
//            return mockCompteCourantsDTO.stream()
//            		.filter(dto->dto.getId().equals(compteCourant.getId()))
//            		.findFirst()
//            		.orElse(null);
//              
//          });
//		
//        List<CompteCourantDTO> comptesCourantsDTO = compteCourantService.getAllCompte();
//        
//        assertEquals(mockCompteCourantsDTO, comptesCourantsDTO);
		
//		 List<CompteCourantDTO> mockCompteCourantsDTO = mockCompteCourant.stream()
//		            .map(compteCourant -> new CompteCourantDTO(
//		                compteCourant.getId(),
//		                compteCourant.getBalance(),
//		                compteCourant.getOverdraft(),
//		                compteCourant.getCarte(),
//		                compteCourant.getClient()
//		            ))
//		            .collect(Collectors.toList());
//
//		        when(compteRepository.findAll()).thenReturn(mockCompteCourant);
//
//		        when(compteCourantMapper.toDto(any(CompteCourant.class)))
//		            .thenAnswer(e -> {
//		                CompteCourant compteCourant = e.getArgument(0);
//		                return mockCompteCourantsDTO.stream()
//		                    .filter(dto -> dto.getId().equals(compteCourant.getId()))
//		                    .findFirst()
//		                    .orElse(null);
//		            });

//		        List<CompteCourantDTO> comptesCourantsDTO = compteCourantService.getAllCompte();
//
//		        assertEquals(mockCompteCourantsDTO, comptesCourantsDTO);
//	
//	}
	
}
