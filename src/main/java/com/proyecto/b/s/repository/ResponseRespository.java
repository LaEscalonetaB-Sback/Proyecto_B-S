package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRespository extends JpaRepository<Response, Long> {
    @Query("SELECT r FROM Response r WHERE r.name = :name")
    Response findByName(@Param("name") String name);
}
