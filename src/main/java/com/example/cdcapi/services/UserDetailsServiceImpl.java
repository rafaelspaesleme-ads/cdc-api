package com.example.cdcapi.services;

import com.example.cdcapi.entities.Usuarios;
import com.example.cdcapi.repositories.UsuariosRepository;
import com.example.cdcapi.security.UsuariosSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuariosRepository usuariosRepository;

    public UserDetailsServiceImpl(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        final Optional<Usuarios> usuarios = usuariosRepository.findByUsuario(usuario);
        if (!usuarios.isPresent()){
            throw new UsernameNotFoundException("Usuário ".concat(usuario).concat(" não encontrado."));
        }

        return new UsuariosSecurity(usuarios.get().getId(), usuarios.get().getUsuario(), usuarios.get().getSenha(), usuarios.get().getPerfis());
    }
}
