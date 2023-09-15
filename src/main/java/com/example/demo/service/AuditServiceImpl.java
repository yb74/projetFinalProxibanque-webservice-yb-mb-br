package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CompteCourantDTO;
import com.example.demo.dto.CompteEpargneDTO;
import com.example.demo.mapper.CompteCourantMapper;
import com.example.demo.mapper.CompteEpargneMapper;
import com.example.demo.model.CompteCourant;
import com.example.demo.model.CompteEpargne;
import com.example.demo.repository.CompteCourantRepository;
import com.example.demo.repository.CompteEpargneRepository;

@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    private CompteCourantMapper compteCourantMapper;

    @Autowired
    private CompteEpargneMapper compteEpargneMapper;

    @Autowired
    private CompteCourantRepository compteCourantRepository;

    @Autowired
    private CompteEpargneRepository compteEpargneRepository;


    @Override
    public List<CompteCourantDTO> getComptesCourantsDebiteurs() {
        List<CompteCourantDTO> comptesCourantsDebiteurs = new ArrayList<>();

        List<CompteCourant> tousLesComptesCourants = compteCourantRepository.findAll();

        for (CompteCourant compteCourant : tousLesComptesCourants) {
            if (compteCourant.getBalance() < -5000) {
                CompteCourantDTO compteCourantDTO = compteCourantMapper.toDto(compteCourant);
                comptesCourantsDebiteurs.add(compteCourantDTO);
            }
        }
        return comptesCourantsDebiteurs;
    }

    @Override
    public List<CompteEpargneDTO> getComptesEpargneDebiteurs() {
        List<CompteEpargneDTO> comptesEpargneDebiteurs = new ArrayList<>();

        List<CompteEpargne> tousLesComptesEpargne = compteEpargneRepository.findAll();

        for (CompteEpargne compteEpargne : tousLesComptesEpargne) {
            if (compteEpargne.getBalance() < -5000) {
                CompteEpargneDTO compteEpargneDTO = compteEpargneMapper.toDto(compteEpargne);
                comptesEpargneDebiteurs.add(compteEpargneDTO);
            }
        }
        return comptesEpargneDebiteurs;
    }
}
