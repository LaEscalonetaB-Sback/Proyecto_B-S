package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Client;
import com.proyecto.b.s.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c WHERE c.name = :name")
    Client findByName(@Param("name") String name);

    @Query(value = "SELECT c FROM Client c " +
            "WHERE (:name IS NULL OR c.name = :name) " +
            "AND (:cuit IS NULL OR c.cuit = :cuit)")
    List<Client> searchBy(
            @Param("name") String name,
            @Param("cuit") Integer cuit);


}
