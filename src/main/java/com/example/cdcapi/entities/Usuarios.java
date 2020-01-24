package com.example.cdcapi.entities;

import com.example.cdcapi.configurations.ProfileUserConfiguration;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true, nullable = false)
    private String usuario;

    @JsonIgnore
    @Column(columnDefinition = "TEXT", nullable = false)
    private String senha;
    private Boolean ativo;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "perfis_usuarios")
    private Set<String> perfis = new HashSet<>();

    @Column(unique = true, nullable = false)
    private String email;

    public Usuarios(Long id, String nome, String usuario, String senha, Boolean ativo, String email, String tipoPerfil) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.ativo = ativo;
        this.email = email;
    }

    public Usuarios() {
        addPerfil(ProfileUserConfiguration.ROLE_USER);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Set<ProfileUserConfiguration> getPerfis() {
        return perfis.stream().map(ProfileUserConfiguration::valueOf).collect(Collectors.toSet());
    }

    public void addPerfil(ProfileUserConfiguration profileUserConfiguration){
        perfis.add(profileUserConfiguration.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuarios usuario = (Usuarios) o;
        return id.equals(usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static final class UsuariosBuilder {
        private Usuarios usuarios;

        private UsuariosBuilder() {
            usuarios = new Usuarios();
        }

        public static UsuariosBuilder anUsuarios() {
            return new UsuariosBuilder();
        }

        public UsuariosBuilder comId(Long id) {
            usuarios.setId(id);
            return this;
        }

        public UsuariosBuilder comNome(String nome) {
            usuarios.setNome(nome);
            return this;
        }

        public UsuariosBuilder comUsuario(String usuario) {
            usuarios.setUsuario(usuario);
            return this;
        }

        public UsuariosBuilder comSenha(String senha) {
            usuarios.setSenha(senha);
            return this;
        }

        public UsuariosBuilder comAtivo(Boolean ativo) {
            usuarios.setAtivo(ativo);
            return this;
        }

        public UsuariosBuilder comEmail(String email) {
            usuarios.setEmail(email);
            return this;
        }

        public Usuarios build() {
            return usuarios;
        }
    }
}
