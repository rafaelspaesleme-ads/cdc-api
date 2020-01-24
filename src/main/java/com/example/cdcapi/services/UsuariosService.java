package com.example.cdcapi.services;

import com.example.cdcapi.configurations.PasswordConfiguration;
import com.example.cdcapi.configurations.ProfileUserConfiguration;
import com.example.cdcapi.dto.UsuariosDTO;
import com.example.cdcapi.entities.Usuarios;
import com.example.cdcapi.exceptions.AuthorizationException;
import com.example.cdcapi.repositories.UsuariosRepository;
import com.example.cdcapi.security.UsuariosSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {
    private Boolean del;
    private final UsuariosRepository usuariosRepository;
    private final PasswordConfiguration passwordConfiguration;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuariosService(UsuariosRepository usuariosRepository, PasswordConfiguration passwordConfiguration, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuariosRepository = usuariosRepository;
        this.passwordConfiguration = passwordConfiguration;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Optional<Usuarios> findById(Long id){
        UsuariosSecurity usuariosSecurity = UserService.authenticated();
        if (usuariosSecurity == null && !usuariosSecurity.hasRole(ProfileUserConfiguration.ROLE_ADMIN) && !id.equals(usuariosSecurity.getId())){
            throw new AuthorizationException("Acesso negado.");
        }
        return usuariosRepository.findById(id);
    }

    public List<Usuarios> findByUsuario(String usuario){
        return usuariosRepository.findAllByUsuario(usuario);
    }

    public List<Usuarios> findByNome(String nome){
        return usuariosRepository.findByNome(nome);
    }

    public List<Usuarios> findByAtivo(Boolean ativo){
        return usuariosRepository.findByAtivo(ativo);
    }

    public List<Usuarios> findByContemUsuario(String usuario){
        return usuariosRepository.findByContemUsuario(usuario);
    }

    public List<Usuarios> findByContemNome(String nome){
        return usuariosRepository.findByContemNome(nome);
    }

    public Usuarios save(UsuariosDTO usuariosDTO){
        usuariosDTO.setId(null);
        return usuariosRepository.save(buildUsuarios(usuariosDTO));
    }

    public Optional<Usuarios> update(Long id, UsuariosDTO usuariosDTO){
        Optional<Usuarios> usuarios = usuariosRepository.findById(id);
        usuarios.ifPresent(u -> usuariosRepository.save(buildUsuarios(usuariosDTO)));
        return usuarios;
    }

    public Boolean delete(Long id){
        Optional<Usuarios> u1 = findById(id);
        u1.ifPresent(a -> usuariosRepository.delete(u1.get()));
        del = true;
        Optional<Usuarios> u2 = findById(id);
        if (!u2.isPresent()) del = false;
        return del;
    }

    public List<Usuarios> findAll(){
        return usuariosRepository.findAll();
    }

    public Usuarios buildUsuarios(UsuariosDTO usuariosDTO){
        return Usuarios.UsuariosBuilder.anUsuarios()
                .comAtivo(usuariosDTO.getAtivo())
                .comId(usuariosDTO.getId())
                .comNome(usuariosDTO.getNome())
                .comUsuario(usuariosDTO.getUsuario())
                .comSenha(bCryptPasswordEncoder.encode(usuariosDTO.getSenha()))
                .comEmail(usuariosDTO.getEmail())
                .build();
    }
}
