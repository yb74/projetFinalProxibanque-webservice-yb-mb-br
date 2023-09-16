package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Conseiller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConseillerRepository extends JpaRepository<Conseiller, Long> {
    /**
     * Custom query to find Conseiller associated with number of Client less than the value passed as parameter
     */
    @Query("SELECT c FROM Conseiller c WHERE SIZE(c.clients) < :maxClients")
    List<Conseiller> findByClientsSizeLessThan(int maxClients);
    
//	Boolean existsByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	Conseiller findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    
}
