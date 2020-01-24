package com.example.cdcapi.repositories;

import com.example.cdcapi.entities.Autores;
import com.example.cdcapi.entities.Categoria;
import com.example.cdcapi.entities.Livros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivrosRepository extends JpaRepository<Livros, Long> {
    List<Livros> findByTitulo(String titulo);
    List<Livros> findBySubTitulo(String subtitulo);
    List<Livros> findByAutor(Autores autores);
    List<Livros> findByEditora(String editora);
    List<Livros> findByCategoria(Categoria categoria);

    @Query(value = "SELECT * FROM livros WHERE titulo LIKE %?1%", nativeQuery = true)
    List<Livros> findByContemTitulo(String titulo);
    @Query(value = "SELECT * FROM livros WHERE sub_titulo LIKE %?1%", nativeQuery = true)
    List<Livros> findByContemSubTitulo(String subTitulo);
    @Query(value = "SELECT * FROM livros WHERE fk_autor LIKE %?1%", nativeQuery = true)
    List<Livros> findByContemAutor(Long autores);
    @Query(value = "SELECT * FROM livros WHERE editora LIKE %?1%", nativeQuery = true)
    List<Livros> findByContemEditora(String editora);
    @Query(value = "SELECT * FROM livros WHERE fk_categoria LIKE %?1%", nativeQuery = true)
    List<Livros> findByContemCategoria(Long categoria);
}
