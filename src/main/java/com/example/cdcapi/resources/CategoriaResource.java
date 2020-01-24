package com.example.cdcapi.resources;

import com.example.cdcapi.dto.CategoriaDTO;
import com.example.cdcapi.entities.Categoria;
import com.example.cdcapi.services.CategoriaService;
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
public class CategoriaResource {
    private final CategoriaService categoriaService;

    public CategoriaResource(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @CrossOrigin
    @GetMapping(value = CATEGORIA_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/codigo/{id}")
    public ResponseEntity<Categoria> findById(@Valid  @PathVariable Long id) {
        Optional<Categoria> categoria = categoriaService.findById(id);
        return ResponseEntity.ok().body(categoria.orElse(null));
    }

    @CrossOrigin
    @GetMapping(value = CATEGORIA_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/espec/descricao/{descricao}")
    public ResponseEntity<List<Categoria>> findByDescricao(@Valid @PathVariable String descricao) {
        List<Categoria> categorias = categoriaService.findByDescricao(descricao);
        return ResponseEntity.ok().body(categorias);
    }

    @CrossOrigin
    @GetMapping(value = CATEGORIA_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/descricao/{descricao}")
    public ResponseEntity<List<Categoria>> findByContemDescricao(@Valid @PathVariable String descricao) {
        List<Categoria> categorias = categoriaService.findByContemDescricao(descricao);
        return ResponseEntity.ok().body(categorias);
    }

    @CrossOrigin
    @GetMapping(value = CATEGORIA_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/situacao/{ativo}")
    public ResponseEntity<List<Categoria>> findByAtivo(@Valid @PathVariable Boolean ativo) {
        List<Categoria> categorias = categoriaService.findByAtivo(ativo);
        return ResponseEntity.ok().body(categorias);
    }

    @CrossOrigin
    @PostMapping(value = CATEGORIA_URL_DEFAULT+"/"+NAME_URL_SAVE)
    public ResponseEntity<Categoria> save(@Valid @RequestBody CategoriaDTO categoriaDTO){
        Categoria categoria = categoriaService.save(categoriaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @CrossOrigin
    @PostMapping(value = CATEGORIA_URL_DEFAULT+"/"+NAME_URL_SAVE_REAL_TIME)
    public ResponseEntity<List<Categoria>> saveRealTime(@Valid @RequestBody CategoriaDTO categoriaDTO){
        Categoria categoria = categoriaService.save(categoriaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(categoria.getId()).toUri();
        List<Categoria> categorias = categoriaService.findAll();
        return ResponseEntity.created(uri).body(categorias);
    }

    @CrossOrigin
    @PutMapping(value = CATEGORIA_URL_DEFAULT+"/"+NAME_URL_UPDATE+"/{id}")
    public ResponseEntity<List<Categoria>> update(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Long id){
        Optional<Categoria> categoria = categoriaService.update(id, categoriaDTO);
        List<Categoria> categorias = categoriaService.findAll();
        return categoria.isPresent() ? ResponseEntity.ok().body(categorias) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(categorias);
    }

    @CrossOrigin
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = CATEGORIA_URL_DEFAULT+"/"+NAME_URL_DELETE+"/{id}")
    public ResponseEntity<List<Categoria>> delete(@Valid @PathVariable Long id){
        Boolean delete = categoriaService.delete(id);
        List<Categoria> categorias = categoriaService.findAll();
        return delete ? ResponseEntity.ok().body(categorias) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(categorias);
    }

    @CrossOrigin
    @GetMapping(value = CATEGORIA_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/todos")
    public ResponseEntity<List<Categoria>> findAll(){
        List<Categoria> categorias = categoriaService.findAll();
        return ResponseEntity.ok().body(categorias);
    }
}
