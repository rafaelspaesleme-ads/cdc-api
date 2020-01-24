package com.example.cdcapi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Autores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    public Autores(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Autores() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autores autores = (Autores) o;
        return id.equals(autores.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static final class AutoresBuilder {
        private Autores autores;

        private AutoresBuilder() {
            autores = new Autores();
        }

        public static AutoresBuilder anAutores() {
            return new AutoresBuilder();
        }

        public AutoresBuilder comId(Long id) {
            autores.setId(id);
            return this;
        }

        public AutoresBuilder comNome(String nome) {
            autores.setNome(nome);
            return this;
        }

        public AutoresBuilder comEmail(String email) {
            autores.setEmail(email);
            return this;
        }

        public Autores build() {
            return autores;
        }
    }
}
