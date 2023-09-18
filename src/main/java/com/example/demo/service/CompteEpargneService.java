package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.CompteEpargneDTO;
import com.example.demo.dto.CompteEpargneDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.model.CompteEpargne;

public interface CompteEpargneService {
    List<CompteEpargneDTO> getAllCompte();

    CompteEpargneDTO getCompteById(Long compteId) throws GeneralException;
    
	Optional<CompteEpargne> getCompteEpargneById(Long compteId);

    CompteEpargneDTO createCompte(CompteEpargneDTO compte);

    CompteEpargneDTO createCompteWithClient(CompteEpargneDTO createCompteEpargneDTO) throws GeneralException;

    CompteEpargneDTO updateCompte(Long compteId, CompteEpargneDTO compte) throws GeneralException;

    void deleteCompte(Long compteId) throws GeneralException;

	Optional<CompteEpargneDTO> getCompteByAccountNumber(String accountNumber);

	Optional<CompteEpargneDTO> getCompteByClientId(Long clientId);

    // AuditDTO doAudit();
}
