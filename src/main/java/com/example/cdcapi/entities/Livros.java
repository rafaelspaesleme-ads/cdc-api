package com.example.cdcapi.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Livros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Column(name = "sub_titulo")
    private String subTitulo;
    @ManyToOne
    @JoinColumn(name = "fk_autor")
    private Autores autor;
    private String editora;
    private String idioma;
    @ManyToOne
    @JoinColumn(name = "fk_categoria")
    private Categoria categoria;

    public Livros(Long id, String titulo, String subTitulo, Autores autor, String editora, String idioma, Categoria categoria) {
        this.id = id;
        this.titulo = titulo;
        this.subTitulo = subTitulo;
        this.autor = autor;
        this.editora = editora;
        this.idioma = idioma;
        this.categoria = categoria;
    }

    public Livros() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livros livros = (Livros) o;
        return id.equals(livros.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static final class LivrosBuilder {
        private Livros livros;

        private LivrosBuilder() {
            livros = new Livros();
        }

        public static LivrosBuilder aLivros() {
            return new LivrosBuilder();
        }

        public LivrosBuilder comId(Long id) {
            livros.setId(id);
            return this;
        }

        public LivrosBuilder comTitulo(String titulo) {
            livros.setTitulo(titulo);
            return this;
        }

        public LivrosBuilder comSubTitulo(String subTitulo) {
            livros.setSubTitulo(subTitulo);
            return this;
        }

        public LivrosBuilder comAutor(Autores autor) {
            livros.setAutor(autor);
            return this;
        }

        public LivrosBuilder comEditora(String editora) {
            livros.setEditora(editora);
            return this;
        }

        public LivrosBuilder comIdioma(String idioma) {
            livros.setIdioma(idioma);
            return this;
        }

        public LivrosBuilder comCategoria(Categoria categoria) {
            livros.setCategoria(categoria);
            return this;
        }

        public Livros build() {
            return livros;
        }
    }
}
