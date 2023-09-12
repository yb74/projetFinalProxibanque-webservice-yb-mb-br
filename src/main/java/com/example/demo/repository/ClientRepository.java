package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Client;


public interface ClientRepository extends JpaRepository<Client, Long> {

}
