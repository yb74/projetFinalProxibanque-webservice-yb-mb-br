package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Carte;

public interface CarteRepository extends JpaRepository<Carte, Long> {

}
