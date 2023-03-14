package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c WHERE c.name = :name")
    Client findByName(@Param("name") String name);
}
