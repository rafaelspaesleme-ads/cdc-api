package com.example.cdcapi.services;

import com.example.cdcapi.dto.AutoresDTO;
import com.example.cdcapi.entities.Autores;
import com.example.cdcapi.repositories.AutoresRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoresService {
    private Boolean del;
    private final AutoresRepository autoresRepository;

    public AutoresService(AutoresRepository autoresRepository) {
        this.autoresRepository = autoresRepository;
    }

    public Optional<Autores> findById(Long id){
        return autoresRepository.findById(id);
    }

    public List<Autores> findByNomes(String nome){
        return autoresRepository.findByNome(nome);
    }

    public List<Autores> findByEmail(String email){
        return autoresRepository.findByEmail(email);
    }

    public List<Autores> findByContemNome(String nome) {
        return autoresRepository.findByContemNome(nome);
    }

    public List<Autores> findByContemEmail(String email) {
        return autoresRepository.findByContemEmail(email);
    }

    public Autores save(AutoresDTO autoresDTO){
        autoresDTO.setId(null);
        return autoresRepository.save(buildAutores(autoresDTO));
    }

    public Optional<Autores> update(AutoresDTO autoresDTO, Long id){
        Optional<Autores> autores = findById(id);
        autores.ifPresent(a -> autoresRepository.save(buildAutores(autoresDTO)));
        return autores;
    }

    public Boolean delete(Long id){
        Optional<Autores> a1 = findById(id);
        a1.ifPresent(a -> autoresRepository.delete(a1.get()));
        del = true;
        Optional<Autores> a2 = findById(id);
        if (!a2.isPresent()) del = false;
        return del;
    }

    public List<Autores> findAll(){
        return autoresRepository.findAll();
    }

    public Autores buildAutores(AutoresDTO autoresDTO){
        return Autores.AutoresBuilder.anAutores()
                .comId(autoresDTO.getId())
                .comNome(autoresDTO.getNome())
                .comEmail(autoresDTO.getEmail())
                .build();
    }
}
