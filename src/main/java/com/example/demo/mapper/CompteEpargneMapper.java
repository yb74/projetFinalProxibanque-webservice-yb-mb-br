package com.example.demo.mapper;

import com.example.demo.dto.CompteEpargneDTO;
import com.example.demo.model.CompteEpargne;
import org.springframework.stereotype.Component;

@Component
public class CompteEpargneMapper {

    public CompteEpargneDTO toDto(CompteEpargne c) {
        return new CompteEpargneDTO(c.getId(), c.getAccountNumber(), c.getBalance(), c.getRemuneration(), c.getClient());
    }

    public CompteEpargne toCompteEpargne(CompteEpargneDTO compteEpargneDTO) {
        return new CompteEpargne(compteEpargneDTO.getAccountNumber(), compteEpargneDTO.getBalance(), compteEpargneDTO.getRemuneration(), compteEpargneDTO.getClient());
    }

}
