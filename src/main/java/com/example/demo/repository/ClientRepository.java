package com.example.demo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	@Query("SELECT c.id FROM Client c WHERE c.conseiller.id = :conseillerId")
	Set<Long> findIdsByConseillerId(Long conseillerId);

}
