package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Search;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Search, Long> {

}
