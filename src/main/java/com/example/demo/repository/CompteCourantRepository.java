package com.example.demo.repository;

import com.example.demo.model.CompteCourant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface CompteCourantRepository extends JpaRepository<CompteCourant, Long> {
	CompteCourant findByAccountNumber(String accountNumber);
	CompteCourant findByClientId(Long clientId);
}
 
