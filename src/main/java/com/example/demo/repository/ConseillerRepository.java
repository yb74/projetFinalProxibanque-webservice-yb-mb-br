package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Conseiller;

public interface ConseillerRepository extends JpaRepository<Conseiller,Long> {

}
