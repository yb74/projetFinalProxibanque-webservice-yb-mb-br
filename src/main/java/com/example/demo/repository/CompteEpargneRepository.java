package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CompteEpargne;

public interface CompteEpargneRepository extends JpaRepository<CompteEpargne, Long> {

	CompteEpargne findByAccountNumber(String accountNumber);}
