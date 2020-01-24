package com.example.cdcapi.services;

import com.example.cdcapi.dto.AutoresDTO;
import com.example.cdcapi.dto.CategoriaDTO;
import com.example.cdcapi.dto.LivrosDTO;
import com.example.cdcapi.entities.Autores;
import com.example.cdcapi.entities.Categoria;
import com.example.cdcapi.entities.Livros;
import com.example.cdcapi.repositories.LivrosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivrosService {
    private Boolean del;
    private final LivrosRepository livrosRepository;
    private final AutoresService autoresService;
    private final CategoriaService categoriaService;

    public LivrosService(LivrosRepository livrosRepository, AutoresService autoresService, CategoriaService categoriaService) {
        this.livrosRepository = livrosRepository;
        this.autoresService = autoresService;
        this.categoriaService = categoriaService;
    }

    public Livros buildLivros(LivrosDTO livrosDTO){
        return Livros.LivrosBuilder.aLivros()
                .comAutor(checkAutores(livrosDTO))
                .comCategoria(checkCategoria(livrosDTO))
                .comEditora(livrosDTO.getEditora())
                .comId(livrosDTO.getId())
                .comIdioma(livrosDTO.getIdioma())
                .comSubTitulo(livrosDTO.getSubTitulo())
                .comTitulo(livrosDTO.getTitulo())
                .build();
    }

    public Autores checkAutores(LivrosDTO livrosDTO){
        return autoresService.findById(autoresService
                .buildAutores(buildAutores(livrosDTO
                        .getAutor())).getId())
                .orElse(null);
    }

    public Categoria checkCategoria(LivrosDTO livrosDTO){
        return categoriaService.findById(categoriaService
                .buildCategoria(buildCategorias(livrosDTO
                        .getCategoria())).getId())
                .orElse(null);
    }

    public CategoriaDTO buildCategorias(Categoria categoria){
        return CategoriaDTO.CategoriaDTOBuilder.aCategoriaDTO()
                .comAtivo(categoria.getAtivo())
                .comDescricao(categoria.getDescricao())
                .comId(categoria.getId())
                .build();
    }

    public AutoresDTO buildAutores(Autores autores){
        return AutoresDTO.AutoresDTOBuilder.anAutoresDTO()
                .comId(autores.getId())
                .comNome(autores.getNome())
                .comEmail(autores.getEmail())
                .build();
    }

    public Optional<Livros> findById(Long id){
        return livrosRepository.findById(id);
    }

    public List<Livros> findByTitulos(String titulo){
        return livrosRepository.findByTitulo(titulo);
    }

    public List<Livros> findByContemTitulos(String titulo){
        return livrosRepository.findByContemTitulo(titulo);
    }

    public List<Livros> findBySubTitulos(String subTitulos) {
        return livrosRepository.findBySubTitulo(subTitulos);
    }

    public List<Livros> findByContemSubTitulos(String subTitulos) {
        return livrosRepository.findByContemSubTitulo(subTitulos);
    }

    public List<Livros> findByAutores(Autores autores) {
            return livrosRepository.findByAutor(autores);
    }

    public List<Livros> findByContemAutores(Autores autores) {
            return livrosRepository.findByContemAutor(autores.getId());
    }

    public List<Livros> findByEditora(String editora){
        return livrosRepository.findByEditora(editora);
    }

    public List<Livros> findByContemEditora(String editora){
        return livrosRepository.findByContemEditora(editora);
    }

    public List<Livros> findByCategoria(Categoria categoria){
            return livrosRepository.findByCategoria(categoria);
    }

    public List<Livros> findByContemCategoria(Categoria categoria){
            return livrosRepository.findByContemCategoria(categoria.getId());
    }

    public List<Livros> findAll(){
        return livrosRepository.findAll();
    }

    public Livros save(LivrosDTO livrosDTO){
        livrosDTO.setId(null);
        return livrosRepository.save(buildLivros(livrosDTO));
    }

    public Optional<Livros> update(Long id, LivrosDTO livrosDTO){
        Optional<Livros> livros = livrosRepository.findById(id);
        livros.ifPresent(l -> livrosRepository.save(buildLivros(livrosDTO)));
        return livros;
    }

    public Boolean delete(Long id){
        Optional<Livros> l1 = findById(id);
        l1.ifPresent(a -> livrosRepository.delete(l1.get()));
        del = true;
        Optional<Livros> l2 = findById(id);
        if (!l2.isPresent()) del = false;
        return del;
    }

}
