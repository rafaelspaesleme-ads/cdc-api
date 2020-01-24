package com.example.cdcapi.resources;

import com.example.cdcapi.dto.AutoresDTO;
import com.example.cdcapi.entities.Autores;
import com.example.cdcapi.services.AutoresService;
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
public class AutoresResource {

    private final AutoresService autoresService;

    public AutoresResource(AutoresService autoresService) {
        this.autoresService = autoresService;
    }

    @CrossOrigin
    @GetMapping(value = AUTORES_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/codigo/{id}")
    public ResponseEntity<Autores> findById(@Valid @PathVariable Long id){
        Optional<Autores> autores = autoresService.findById(id);
        return ResponseEntity.ok().body(autores.orElse(null));
    }

    @CrossOrigin
    @GetMapping(value = AUTORES_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/espec/nome/{nome}")
    public ResponseEntity<List<Autores>> findByNome(@Valid @PathVariable String nome){
        List<Autores> autores = autoresService.findByNomes(nome);
        return ResponseEntity.ok().body(autores);
    }

    @CrossOrigin
    @GetMapping(value = AUTORES_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/nome/{nome}")
    public ResponseEntity<List<Autores>> findByContemNome(@Valid @PathVariable String nome){
        List<Autores> autores = autoresService.findByContemNome(nome);
        return ResponseEntity.ok().body(autores);
    }

    @CrossOrigin
    @GetMapping(value = AUTORES_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/espec/email/{email}")
    public ResponseEntity<List<Autores>> findByEmail(@Valid @PathVariable String email){
        List<Autores> autores = autoresService.findByEmail(email);
        return ResponseEntity.ok().body(autores);
    }

    @CrossOrigin
    @GetMapping(value = AUTORES_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/email/{email}")
    public ResponseEntity<List<Autores>> findByContemEmail(@Valid @PathVariable String email){
        List<Autores> autores = autoresService.findByContemEmail(email);
        return ResponseEntity.ok().body(autores);
    }

    @CrossOrigin
    @GetMapping(value = AUTORES_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/todos")
    public ResponseEntity<List<Autores>> findAll(){
        List<Autores> autores = autoresService.findAll();
        return ResponseEntity.ok().body(autores);
    }

    @CrossOrigin
    @PostMapping(value = AUTORES_URL_DEFAULT+"/"+NAME_URL_SAVE)
    public ResponseEntity<Autores> save(@Valid @RequestBody AutoresDTO autoresDTO){
        Autores autores = autoresService.save(autoresDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(autores.getId()).toUri();
        return ResponseEntity.created(uri).body(autores);
    }

    @CrossOrigin
    @PostMapping(value = AUTORES_URL_DEFAULT+"/"+NAME_URL_SAVE_REAL_TIME)
    public ResponseEntity<List<Autores>> saveRealTime(@Valid @RequestBody AutoresDTO autoresDTO){
        Autores autores = autoresService.save(autoresDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(autores.getId()).toUri();
        List<Autores> autoresList = autoresService.findAll();
        return ResponseEntity.created(uri).body(autoresList);
    }

    @CrossOrigin
    @PutMapping(value = AUTORES_URL_DEFAULT+"/"+NAME_URL_UPDATE+"/{id}")
    public ResponseEntity<List<Autores>> update(@Valid @RequestBody AutoresDTO autoresDTO, @PathVariable Long id){
        Optional<Autores> autores = autoresService.update(autoresDTO, id);
        List<Autores> autoresList = autoresService.findAll();
        return autores.isPresent() ? ResponseEntity.ok().body(autoresList) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(autoresList);
    }

    @CrossOrigin
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = AUTORES_URL_DEFAULT+"/"+NAME_URL_DELETE+"/{id}")
    public ResponseEntity<List<Autores>> delete(@Valid @PathVariable Long id){
        Boolean delete = autoresService.delete(id);
        List<Autores> autoresList = autoresService.findAll();
        return delete ? ResponseEntity.ok().body(autoresList) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(autoresList);
    }

}
