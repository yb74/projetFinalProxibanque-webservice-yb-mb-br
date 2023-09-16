package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ConseillerDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.mapper.ConseillerMapper;
import com.example.demo.model.Client;
import com.example.demo.model.CompteCourant;
import com.example.demo.model.Conseiller;
import com.example.demo.model.Transaction;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.ConseillerRepository;
import com.example.demo.repository.TransactionRepository;

@Service
public class ConseillerServiceImpl implements ConseillerService {
	private final ConseillerRepository conseillerRepository;
	private final ClientRepository clientRepository;
	private final TransactionRepository transactionRepository;

	@Autowired
	private ConseillerMapper mapper;

	public ConseillerServiceImpl(ConseillerRepository conseillerRepository, ClientRepository clientRepository,
			TransactionRepository transactionRepository) {
		this.conseillerRepository = conseillerRepository;
		this.clientRepository = clientRepository;
		this.transactionRepository = transactionRepository;
	}

	@Override
	public List<Conseiller> getAllConseillers() {
		return (List<Conseiller>) conseillerRepository.findAll();
	}

	@Override
	public Optional<ConseillerDTO> getConseillerById(Long id) throws GeneralException {
		Optional<Conseiller> optionalConseiller = conseillerRepository.findById(id);

		if (optionalConseiller.isPresent()) {
			ConseillerDTO conseillerDto = mapper.ToDto(optionalConseiller.get());
			return Optional.of(conseillerDto);
		} else {
			throw new GeneralException("Conseiller not found with ID: " + id);
		}
	}

	@Override
	public ConseillerDTO createConseiller(ConseillerDTO conseillerDTO) {
		Conseiller conseiller = mapper.toConseiller(conseillerDTO);
		Conseiller savedConseiller = conseillerRepository.save(conseiller);
		return mapper.ToDto(savedConseiller);
	}

	@Override
	public ConseillerDTO updateConseiller(Long id, ConseillerDTO conseillerDTO) throws GeneralException {
		Optional<Conseiller> existingConseillerOptional = conseillerRepository.findById(id);

		if (existingConseillerOptional.isEmpty()) {
//            throw new ResourceNotFoundException("Conseiller not found with id: " + id);
			throw new GeneralException("Conseiller not found with id: " + id);
		}
		Conseiller existingConseiller = existingConseillerOptional.get();
		existingConseiller.setName(conseillerDTO.getName());
		existingConseiller.setFirstname(conseillerDTO.getFirstname());
		Conseiller updatedConseiller = conseillerRepository.save(existingConseiller);
		return mapper.ToDto(updatedConseiller);

	}

	@Override
	public void deleteConseiller(Long conseillerId) throws GeneralException {
		Conseiller conseiller = conseillerRepository.findById(conseillerId)
				.orElseThrow(() -> new GeneralException("Conseiller not found with ID: " + conseillerId));

		Set<Client> clients = conseiller.getClients();

		// Disassociate each Client from the Conseiller
		for (Client client : clients) {
			client.setConseiller(null);
		}

		// Delete the Conseiller
		conseillerRepository.delete(conseiller);
	}

	@Override
	public Optional<Conseiller> getRealConseillerById(Long conseillerId) throws GeneralException {
		Optional<Conseiller> optionalConseiller = conseillerRepository.findById(conseillerId);
		if (optionalConseiller.isEmpty()) {
			throw new GeneralException("Conseiller not found with ID: " + conseillerId);
		}
		return optionalConseiller;
	}
	
	@Override
	public ConseillerDTO login(String username, String password) throws GeneralException {
//		Boolean isUserExist = conseillerRepository.existsByUsernameAndPassword(username, password);
		Conseiller ExistingUser = conseillerRepository.findByUsernameAndPassword(username, password);
		if (ExistingUser == null) {
			throw new GeneralException("User not found");
		}
		
		return mapper.ToDto(ExistingUser);
	}

//	@Override
//    public Conseiller getInfoForLogged(String username, String password) throws GeneralException {
//		Conseiller user = conseillerRepository.findByUsernameAndPassword(username, password);
//		if (user == null) {
//			throw new GeneralException("User not found");
//		}
//        return user;
//    }

}
