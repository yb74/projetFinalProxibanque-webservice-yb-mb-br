package com.example.demo.mapper;

import com.example.demo.dto.CompteCourantDTO;
import com.example.demo.model.CompteCourant;
import org.springframework.stereotype.Component;

@Component
public class CompteCourantMapper {

    public CompteCourantDTO toDto(CompteCourant c) {
        return new CompteCourantDTO(c.getId(), c.getBalance(), c.getOverdraft(), c.getCarte(), c.getClient());
    }

    public CompteCourant toCompteCourant(CompteCourantDTO compteCourantDTO) {
        return new CompteCourant(compteCourantDTO.getBalance(), compteCourantDTO.getOverdraft(), compteCourantDTO.getCarte(), compteCourantDTO.getClient());
    }
}
