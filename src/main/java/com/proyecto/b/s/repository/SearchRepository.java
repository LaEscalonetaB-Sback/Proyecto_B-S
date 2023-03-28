package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Search, Long> {
    @Query(value = "SELECT b FROM Search b "
            + "LEFT JOIN b.stateSearch eb "
            + "LEFT JOIN b.skills s "
            + "WHERE "
            + "(:client IS NULL OR b.client.name = :client) AND "
            + "(:rol IS NULL OR b.rol.name = :rol) AND "
            + "(:state IS NULL OR eb.name = :state) AND "
            + "(:seniority IS NULL OR b.seniority.name = :seniority) AND "
            + "(COALESCE(:skills, NULL) IS NULL OR EXISTS "
            + "(SELECT 1 FROM b.skills s2 WHERE s2.name IN (:skills)))")
    List<Search> findSearchBy(@Param("client") String client,
                              @Param("rol") String rol,
                              @Param("state") String state,
                              @Param("seniority") String seniority,
                              @Param("skills") List<String> skills);
}
