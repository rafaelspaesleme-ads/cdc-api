package com.example.cdcapi.repositories;

import com.example.cdcapi.entities.Autores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoresRepository extends JpaRepository<Autores, Long> {
    List<Autores> findByEmail(String email);
    List<Autores> findByNome(String nome);

    @Query(value = "SELECT * FROM autores WHERE nome LIKE %?1%", nativeQuery = true)
    List<Autores> findByContemNome(String nome);

    @Query(value = "SELECT * FROM autores WHERE email LIKE %?1%", nativeQuery = true)
    List<Autores> findByContemEmail(String email);
}
