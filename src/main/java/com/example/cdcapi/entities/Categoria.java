package com.example.cdcapi.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String descricao;
    private Boolean ativo;

    public Categoria(Long id, String descricao, Boolean ativo) {
        this.id = id;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public Categoria() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return id.equals(categoria.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static final class CategoriaBuilder {
        private Categoria categoria;

        private CategoriaBuilder() {
            categoria = new Categoria();
        }

        public static CategoriaBuilder aCategoria() {
            return new CategoriaBuilder();
        }

        public CategoriaBuilder comId(Long id) {
            categoria.setId(id);
            return this;
        }

        public CategoriaBuilder comDescricao(String descricao) {
            categoria.setDescricao(descricao);
            return this;
        }

        public CategoriaBuilder comAtivo(Boolean ativo) {
            categoria.setAtivo(ativo);
            return this;
        }

        public Categoria build() {
            return categoria;
        }
    }
}
