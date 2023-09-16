package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CompteCourant;


public interface CompteCourantRepository extends JpaRepository<CompteCourant, Long> {
	CompteCourant findByAccountNumber(String accountNumber);
	CompteCourant findByClient_Id(Long clientId);
}
 
