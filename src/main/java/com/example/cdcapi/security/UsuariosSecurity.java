package com.example.cdcapi.security;

import com.example.cdcapi.configurations.ProfileUserConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UsuariosSecurity implements UserDetails {

    private Long id;
    private String usuario;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

    public UsuariosSecurity() {
    }

    public UsuariosSecurity(Long id, String usuario, String senha, Set<ProfileUserConfiguration> perfis) {
        super();
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.authorities = perfis.stream().map(p -> new SimpleGrantedAuthority(p.toString())).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(ProfileUserConfiguration profileUserConfiguration){
        return getAuthorities().contains(new SimpleGrantedAuthority(profileUserConfiguration.toString()));
    }
}
