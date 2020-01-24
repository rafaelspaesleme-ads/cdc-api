package com.example.cdcapi.repositories;

import com.example.cdcapi.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByDescricao(String descricao);
    List<Categoria> findByAtivo(Boolean ativo);

    @Query(value = "SELECT * FROM categoria WHERE descricao LIKE %?1%", nativeQuery = true)
    List<Categoria> findByContemDescricao(String descricao);
}
