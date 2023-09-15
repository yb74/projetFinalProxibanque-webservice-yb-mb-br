package com.example.demo.service;

import com.example.demo.dto.CompteEpargneDTO;
import com.example.demo.dto.CreateCompteEpargneDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.model.CompteEpargne;

import java.util.List;
import java.util.Optional;

public interface CompteEpargneService {
    List<CompteEpargneDTO> getAllCompte();

    CompteEpargneDTO getCompteById(Long compteId) throws GeneralException;
    
	Optional<CompteEpargne> getCompteEpargneById(Long compteId);

    CompteEpargneDTO createCompte(CompteEpargneDTO compte);

    CompteEpargneDTO createCompteWithClient(CreateCompteEpargneDTO createCompteEpargneDTO) throws GeneralException;

    CompteEpargneDTO updateCompte(Long compteId, CompteEpargneDTO compte) throws GeneralException;

    void deleteCompte(Long compteId) throws GeneralException;

    // AuditDTO doAudit();
}
