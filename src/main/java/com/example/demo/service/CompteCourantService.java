package com.example.demo.service;

import com.example.demo.dto.CompteCourantDTO;
import com.example.demo.dto.CreateCompteCourantDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.model.Carte;
import com.example.demo.model.CompteCourant;

import java.util.List;
import java.util.Optional;

public interface CompteCourantService {
    List<CompteCourantDTO> getAllCompte();

    CompteCourantDTO getCompteById(Long compteId) throws GeneralException;
    
	Optional<CompteCourant> getCompteCourantById(Long compteId);

    CompteCourantDTO createCompte(CompteCourantDTO compte);

     CompteCourantDTO createCompteWithClientAndCarte(CreateCompteCourantDTO createCompteCourantDTO) throws GeneralException;

    CompteCourantDTO updateCompte(Long compteId, CompteCourantDTO compte) throws GeneralException;

    void deleteCompte(Long compteId) throws GeneralException;

    // AuditDTO doAudit();

    void addCarte(CompteCourant comptec, Carte carte);

    void removeCarte(CompteCourant comptec, Carte carte);
}
