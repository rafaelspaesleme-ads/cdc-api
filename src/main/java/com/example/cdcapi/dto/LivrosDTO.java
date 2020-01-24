package com.example.cdcapi.dto;

import com.example.cdcapi.entities.Autores;
import com.example.cdcapi.entities.Categoria;

public class LivrosDTO {
    private Long id;
    private String titulo;
    private String subTitulo;
    private Autores autor;
    private String editora;
    private String idioma;
    private Categoria categoria;

    public LivrosDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    public Autores getAutor() {
        return autor;
    }

    public void setAutor(Autores autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public static final class LivrosDTOBuilder {
        private LivrosDTO livrosDTO;

        private LivrosDTOBuilder() {
            livrosDTO = new LivrosDTO();
        }

        public static LivrosDTOBuilder aLivrosDTO() {
            return new LivrosDTOBuilder();
        }

        public LivrosDTOBuilder comId(Long id) {
            livrosDTO.setId(id);
            return this;
        }

        public LivrosDTOBuilder comTitulo(String titulo) {
            livrosDTO.setTitulo(titulo);
            return this;
        }

        public LivrosDTOBuilder comSubTitulo(String subTitulo) {
            livrosDTO.setSubTitulo(subTitulo);
            return this;
        }

        public LivrosDTOBuilder comAutor(Autores autor) {
            livrosDTO.setAutor(autor);
            return this;
        }

        public LivrosDTOBuilder comEditora(String editora) {
            livrosDTO.setEditora(editora);
            return this;
        }

        public LivrosDTOBuilder comIdioma(String idioma) {
            livrosDTO.setIdioma(idioma);
            return this;
        }

        public LivrosDTOBuilder comCategoria(Categoria categoria) {
            livrosDTO.setCategoria(categoria);
            return this;
        }

        public LivrosDTO build() {
            return livrosDTO;
        }
    }
}
