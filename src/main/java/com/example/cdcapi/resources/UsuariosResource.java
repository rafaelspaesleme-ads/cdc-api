package com.example.cdcapi.resources;

import com.example.cdcapi.dto.UsuariosDTO;
import com.example.cdcapi.entities.Usuarios;
import com.example.cdcapi.services.UsuariosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static com.example.cdcapi.resources.utils.NamedUrlUtil.*;

@RestController
@RequestMapping(value = "/api")
public class UsuariosResource {
    private final UsuariosService usuariosService;

    public UsuariosResource(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @CrossOrigin
    @GetMapping(value = USUARIOS_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/codigo/{id}")
    public ResponseEntity<Usuarios> findById(@PathVariable Long id){
        Optional<Usuarios> usuarios = usuariosService.findById(id);
        return ResponseEntity.ok().body(usuarios.orElse(null));
    }

    @CrossOrigin
    @GetMapping(value = USUARIOS_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/espec/user/{usuario}")
    public ResponseEntity<List<Usuarios>> findByUsuario(@PathVariable String usuario){
        List<Usuarios> usuarios = usuariosService.findByUsuario(usuario);
        return ResponseEntity.ok().body(usuarios);
    }

    @CrossOrigin
    @GetMapping(value = USUARIOS_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/user/{usuario}")
    public ResponseEntity<List<Usuarios>> findByContemUsuario(@PathVariable String usuario){
        List<Usuarios> usuarios = usuariosService.findByContemUsuario(usuario);
        return ResponseEntity.ok().body(usuarios);
    }

    @CrossOrigin
    @GetMapping(value = USUARIOS_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/espec/nome/{nome}")
    public ResponseEntity<List<Usuarios>> findByNome(@PathVariable String nome){
        List<Usuarios> usuarios = usuariosService.findByNome(nome);
        return ResponseEntity.ok().body(usuarios);
    }

    @CrossOrigin
    @GetMapping(value = USUARIOS_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/nome/{nome}")
    public ResponseEntity<List<Usuarios>> findByContemNome(@PathVariable String nome){
        List<Usuarios> usuarios = usuariosService.findByContemNome(nome);
        return ResponseEntity.ok().body(usuarios);
    }

    @CrossOrigin
    @GetMapping(value = USUARIOS_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/situacao/{ativo}")
    public ResponseEntity<List<Usuarios>> findByNome(@PathVariable Boolean ativo){
        List<Usuarios> usuarios = usuariosService.findByAtivo(ativo);
        return ResponseEntity.ok().body(usuarios);
    }

    @CrossOrigin
    @PostMapping(value = USUARIOS_URL_DEFAULT+"/"+NAME_URL_SAVE_REAL_TIME)
    public ResponseEntity<List<Usuarios>> saveRealTime(@Valid @RequestBody UsuariosDTO usuariosDTO){
        Usuarios usuarios = usuariosService.save(usuariosDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(usuarios.getId()).toUri();
        List<Usuarios> usuariosList = usuariosService.findAll();
        return ResponseEntity.created(uri).body(usuariosList);
    }

    @CrossOrigin
    @PutMapping(value = USUARIOS_URL_DEFAULT+"/"+NAME_URL_UPDATE+"/{id}")
    public ResponseEntity<List<Usuarios>> update(@Valid @RequestBody UsuariosDTO usuariosDTO, @PathVariable Long id){
        Optional<Usuarios> usuarios = usuariosService.update(id, usuariosDTO);
        List<Usuarios> usuariosList = usuariosService.findAll();
        return usuarios.isPresent() ? ResponseEntity.ok().body(usuariosList) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(usuariosList);
    }

    @CrossOrigin
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = USUARIOS_URL_DEFAULT+"/"+NAME_URL_DELETE+"/{id}")
    public ResponseEntity<List<Usuarios>> delete(@Valid @PathVariable Long id){
        Boolean delete = usuariosService.delete(id);
        List<Usuarios> usuarios = usuariosService.findAll();
        return delete ? ResponseEntity.ok().body(usuarios) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(usuarios);
    }

    @CrossOrigin
    @GetMapping(value = USUARIOS_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/todos")
    public ResponseEntity<List<Usuarios>> findAll(){
        List<Usuarios> usuarios = usuariosService.findAll();
        return ResponseEntity.ok().body(usuarios);
    }
}
