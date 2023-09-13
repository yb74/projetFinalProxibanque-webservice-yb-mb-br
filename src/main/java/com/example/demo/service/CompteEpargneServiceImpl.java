package com.example.demo.service;

import com.example.demo.dto.CompteEpargneDTO;
import com.example.demo.dto.CreateCompteEpargneDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.mapper.CompteEpargneMapper;
import com.example.demo.model.Client;
import com.example.demo.model.CompteEpargne;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.CompteEpargneRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompteEpargneServiceImpl implements CompteEpargneService {
    private static final Logger LOG = LoggerFactory.getLogger(CompteEpargne.class);

    @Autowired
    private CompteEpargneRepository compteRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CompteEpargneMapper compteEpargneMapper;

    public List<CompteEpargneDTO> getAllCompte() {
        List<CompteEpargne> compteEpargnes = compteRepository.findAll();
        return compteEpargnes.stream()
                .map(compteEpargne -> compteEpargneMapper.toDto(compteEpargne))
                .collect(Collectors.toList());
    }

    @Override
    public CompteEpargneDTO getCompteById(Long compteId) throws GeneralException {
        CompteEpargne compteEpargne = compteRepository.findById(compteId)
                .orElseThrow(() -> new GeneralException("Compte not found with ID: " + compteId));

        return compteEpargneMapper.toDto(compteEpargne);
    }

    @Override
    public CompteEpargneDTO  createCompte(CompteEpargneDTO  compteDTO) {
        CompteEpargne compteEpargne = compteEpargneMapper.toCompteEpargne(compteDTO);
        compteEpargne = compteRepository.save(compteEpargne);
        return compteEpargneMapper.toDto(compteEpargne);
    }

    @Override
    public CompteEpargneDTO updateCompte(Long compteId, CompteEpargneDTO compteDto) throws GeneralException {
        CompteEpargne existingCompte = compteRepository.findById(compteId)
                .orElseThrow(() -> new GeneralException("Compte Epargne with ID " + compteId + " not found"));

        // Check if solde is provided in the request before updating (can't check if solde is not null as it's a primitive double and not Double)
        if (compteDto.getBalance() != 0.0) {
            existingCompte.setBalance(compteDto.getBalance());
        }

        CompteEpargne updatedCompte = compteRepository.save(existingCompte);
        return compteEpargneMapper.toDto(updatedCompte);
    }

    @Override
    @Transactional
    public void deleteCompte(Long compteId) throws GeneralException {
        CompteEpargne compteEpargne = compteRepository.findById(compteId)
                .orElseThrow(() -> new GeneralException("CompteCourant not found with ID: " + compteId));

        // if the account to delete is found, we set the CompteEpargne of the associated Client to null before deletion to avoid constraint to block deletion in the DB
        Client associatedClient = compteEpargne.getClient();
        if (associatedClient != null) associatedClient.setCompteEpargne(null);

        LOG.info("Deleting account with ID: " + compteId);
        compteRepository.deleteById(compteId);
        LOG.info("Account deleted successfully.");
    }

//    @Override
//    public AuditDTO doAudit() {
//        List<CompteEpargneDTO> compteEpargneDTOs = compteRepository.findAll().stream()
//                .filter(compte -> compte.getClass() == CompteEpargne.class && compte.getSolde() < 7100)
//                .map(compte -> entityToDto((CompteEpargne) compte))
//                .collect(Collectors.toList());
//
//        double sommeAudit = compteEpargneDTOs.stream()
//                .mapToDouble(CompteEpargneDTO::getSolde)
//                .sum();
//
//        return new AuditDTO(sommeAudit);
//    }

    @Override
    public CompteEpargneDTO createCompteWithClient(CreateCompteEpargneDTO createCompteEpargneDTO) throws GeneralException {
        CompteEpargneDTO compteDto = createCompteEpargneDTO.getCompte();
        Long clientId = createCompteEpargneDTO.getClientId();

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new GeneralException("Client with ID " + clientId + " not found"));

        CompteEpargne compteEpargne = compteEpargneMapper.toCompteEpargne(compteDto); // Convert DTO to entity
        compteEpargne.setClient(client);
        client.setCompteEpargne(compteEpargne);

        CompteEpargne savedCompte = compteRepository.save(compteEpargne); // Save the entity
        return compteEpargneMapper.toDto(savedCompte); // Convert the saved entity to DTO
    }
}