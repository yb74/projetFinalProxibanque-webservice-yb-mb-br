package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Client;
import com.example.demo.model.CompteEpargne;
import java.util.List;


public interface CompteEpargneRepository extends JpaRepository<CompteEpargne, Long> {

	CompteEpargne findByAccountNumber(String accountNumber);
	

	CompteEpargne findByClient_Id(Long clientId);
}