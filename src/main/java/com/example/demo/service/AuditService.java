package com.example.demo.service;

import com.example.demo.dto.CompteCourantDTO;
import com.example.demo.dto.CompteEpargneDTO;
import com.example.demo.model.CompteCourant;
import com.example.demo.model.CompteEpargne;

import java.util.List;

public interface AuditService {
    public List<CompteCourantDTO> getComptesCourantsDebiteurs();
    public List<CompteEpargneDTO> getComptesEpargneDebiteurs();
}
