package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ConseillerDTO;
import com.example.demo.mapper.ConseillerMapper;
import com.example.demo.model.Conseiller;
import com.example.demo.repository.ConseillerRepository;

@Service
public class ConseillerServiceImpl implements ConseillerService {

	private final ConseillerRepository consillerRepository;

	@Autowired
	private ConseillerMapper mapper;

	public ConseillerServiceImpl(ConseillerRepository consillerRepository) {
		this.consillerRepository = consillerRepository;
	}

	@Override
	public List<Conseiller> getAllConseillers() {
		return (List<Conseiller>) consillerRepository.findAll();
	}

	@Override
	public Optional<Conseiller> getConseillerById(Long id) {
		return consillerRepository.findById(id);
	}

	@Override
	public ConseillerDTO createConseiller(ConseillerDTO conseillerDTO) {
		Conseiller conseiller = mapper.toConseiller(conseillerDTO);
		Conseiller savedConseiller = consillerRepository.save(conseiller);
		return mapper.ToDto(savedConseiller);
	}

	@Override
	public ConseillerDTO updateConseiller(Long id, ConseillerDTO conseillerDTO) {
		Optional<Conseiller> existingConseillerOptional = consillerRepository.findById(id);
		if (existingConseillerOptional.isEmpty()) {
//            throw new ResourceNotFoundException("Conseiller not found with id: " + id);
			throw new RuntimeException("Conseiller not found with id: " + id);
		}
		Conseiller existingConseiller = existingConseillerOptional.get();
		existingConseiller.setName(conseillerDTO.getName());
		existingConseiller.setFirstName(conseillerDTO.getFirstName());
		Conseiller updatedConseiller = consillerRepository.save(existingConseiller);
		return mapper.ToDto(updatedConseiller);

	}

	@Override
	public void deleteConseiller(Long id) {
		consillerRepository.deleteById(id);
	}

}
