package com.example.cdcapi.services;

import com.example.cdcapi.dto.CategoriaDTO;
import com.example.cdcapi.entities.Categoria;
import com.example.cdcapi.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    private Boolean del;
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Optional<Categoria> findById(Long id){
        return categoriaRepository.findById(id);
    }

    public Categoria buildCategoria(CategoriaDTO categoriaDTO){
        return Categoria.CategoriaBuilder.aCategoria()
                .comAtivo(checkAtivo(categoriaDTO.getAtivo()))
                .comDescricao(categoriaDTO.getDescricao())
                .comId(categoriaDTO.getId())
                .build();
    }

    public Boolean checkAtivo(Boolean ativo){
        return ativo != null && ativo;
    }

    public List<Categoria> findByDescricao(String descricao){
        return categoriaRepository.findByDescricao(descricao);
    }

    public List<Categoria> findByContemDescricao(String descricao){
        return categoriaRepository.findByContemDescricao(descricao);
    }

    public List<Categoria> findByAtivo(Boolean ativo){
        return categoriaRepository.findByAtivo(ativo);
    }

    public Categoria save(CategoriaDTO categoriaDTO){
        categoriaDTO.setId(null);
        return categoriaRepository.save(buildCategoria(categoriaDTO));
    }

    public Optional<Categoria> update(Long id, CategoriaDTO categoriaDTO){
        Optional<Categoria> categoria = findById(id);
        categoria.ifPresent(c -> categoriaRepository.save(buildCategoria(categoriaDTO)));
        return categoria;
    }

    public Boolean delete(Long id) {
        Optional<Categoria> c1 = findById(id);
        c1.ifPresent(a -> categoriaRepository.delete(c1.get()));
        del = true;
        Optional<Categoria> c2 = findById(id);
        if (!c2.isPresent()) del = false;
        return del;
    }

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }
}
