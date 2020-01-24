package com.example.cdcapi.resources;

import com.example.cdcapi.dto.LivrosDTO;
import com.example.cdcapi.entities.Autores;
import com.example.cdcapi.entities.Categoria;
import com.example.cdcapi.entities.Livros;
import com.example.cdcapi.services.AutoresService;
import com.example.cdcapi.services.CategoriaService;
import com.example.cdcapi.services.LivrosService;
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
public class LivrosResource {
    private final LivrosService livrosService;
    private final AutoresService autoresService;
    private final CategoriaService categoriaService;

    public LivrosResource(LivrosService livrosService, AutoresService autoresService, CategoriaService categoriaService) {
        this.livrosService = livrosService;
        this.autoresService = autoresService;
        this.categoriaService = categoriaService;
    }

    @CrossOrigin
    @GetMapping(value = LIVROS_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/codigo/{id}")
    public ResponseEntity<Livros> findById(@Valid @PathVariable Long id){
        Optional<Livros> livros = livrosService.findById(id);
        return ResponseEntity.ok().body(livros.orElse(null));
    }

    @CrossOrigin
    @GetMapping(value = LIVROS_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/espec/titulo/{titulo}")
    public ResponseEntity<List<Livros>> findByTitulo(@Valid @PathVariable String titulo){
        List<Livros> livros = livrosService.findByTitulos(titulo);
        return livros != null ? ResponseEntity.ok().body(livros) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @CrossOrigin
    @GetMapping(value = LIVROS_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/titulo/{titulo}")
    public ResponseEntity<List<Livros>> findByContemTitulo(@Valid @PathVariable String titulo){
        List<Livros> livros = livrosService.findByContemTitulos(titulo);
        return livros != null ? ResponseEntity.ok().body(livros) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @CrossOrigin
    @GetMapping(value = LIVROS_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/espec/subtitulo/{subTitulo}")
    public ResponseEntity<List<Livros>> findBySubTitulo(@Valid @PathVariable String subTitulo){
        List<Livros> livros = livrosService.findBySubTitulos(subTitulo);
        return livros != null ? ResponseEntity.ok().body(livros) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @CrossOrigin
    @GetMapping(value = LIVROS_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/subtitulo/{subTitulo}")
    public ResponseEntity<List<Livros>> findByContemSubTitulo(@Valid @PathVariable String subTitulo){
        List<Livros> livros = livrosService.findByContemSubTitulos(subTitulo);
        return livros != null ? ResponseEntity.ok().body(livros) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @CrossOrigin
    @GetMapping(value = LIVROS_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/espec/autores/{idAutor}")
    public ResponseEntity<List<Livros>> findByAutores(@Valid @PathVariable Long idAutor){
        Optional<Autores> autores = autoresService.findById(idAutor);
        List<Livros> livros = livrosService.findByAutores(autores.get());
        return livros != null ? ResponseEntity.ok().body(livros) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @CrossOrigin
    @GetMapping(value = LIVROS_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/autores/{idAutor}")
    public ResponseEntity<List<Livros>> findByContemAutores(@Valid @PathVariable Long idAutor){
        Optional<Autores> autores = autoresService.findById(idAutor);
        List<Livros> livros = livrosService.findByContemAutores(autores.get());
        return livros != null ? ResponseEntity.ok().body(livros) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @CrossOrigin
    @GetMapping(value = LIVROS_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/espec/editora/{editora}")
    public ResponseEntity<List<Livros>> findByEditora(@Valid @PathVariable String editora){
        List<Livros> livros = livrosService.findByEditora(editora);
        return livros != null ? ResponseEntity.ok().body(livros) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @CrossOrigin
    @GetMapping(value = LIVROS_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/editora/{editora}")
    public ResponseEntity<List<Livros>> findByContemEditora(@Valid @PathVariable String editora){
        List<Livros> livros = livrosService.findByContemEditora(editora);
        return livros != null ? ResponseEntity.ok().body(livros) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @CrossOrigin
    @GetMapping(value = LIVROS_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/espec/categoria/{idCategoria}")
    public ResponseEntity<List<Livros>> findByCategoria(@Valid @PathVariable Long idCategoria){
        Optional<Categoria> categorias = categoriaService.findById(idCategoria);
        List<Livros> livros = livrosService.findByCategoria(categorias.get());
        return livros != null ? ResponseEntity.ok().body(livros) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @CrossOrigin
    @GetMapping(value = LIVROS_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/categoria/{idCategoria}")
    public ResponseEntity<List<Livros>> findByContemCategoria(@Valid @PathVariable Long idCategoria){
        Optional<Categoria> categorias = categoriaService.findById(idCategoria);
        List<Livros> livros = livrosService.findByContemCategoria(categorias.get());
        return livros != null ? ResponseEntity.ok().body(livros) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @CrossOrigin
    @PostMapping(value = LIVROS_URL_DEFAULT+"/"+NAME_URL_SAVE)
    public ResponseEntity<Livros> save(@Valid @RequestBody LivrosDTO livrosDTO){
        Livros livros = livrosService.save(livrosDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(livros.getId()).toUri();
        return ResponseEntity.created(uri).body(livros);
    }

    @CrossOrigin
    @PostMapping(value = LIVROS_URL_DEFAULT+"/"+NAME_URL_SAVE_REAL_TIME)
    public ResponseEntity<List<Livros>> saveRealTime(@Valid @RequestBody LivrosDTO livrosDTO){
        Livros livros = livrosService.save(livrosDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(livros.getId()).toUri();
        List<Livros> livrosList = livrosService.findAll();
        return ResponseEntity.created(uri).body(livrosList);
    }

    @CrossOrigin
    @PutMapping(value = LIVROS_URL_DEFAULT+"/"+NAME_URL_UPDATE+"/{id}")
    public ResponseEntity<List<Livros>> update(@Valid @RequestBody LivrosDTO livrosDTO, @PathVariable Long id){
        Optional<Livros> livros = livrosService.update(id, livrosDTO);
        List<Livros> livrosList = livrosService.findAll();
        return livros.isPresent() ? ResponseEntity.ok().body(livrosList) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(livrosList);
    }

    @CrossOrigin
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = LIVROS_URL_DEFAULT+"/"+NAME_URL_DELETE+"/{id}")
    public ResponseEntity<List<Livros>> delete(@Valid @PathVariable Long id){
        Boolean delete = livrosService.delete(id);
        List<Livros> livros = livrosService.findAll();
        return delete ? ResponseEntity.ok().body(livros) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(livros);
    }

    @CrossOrigin
    @GetMapping(value = LIVROS_URL_DEFAULT+"/"+NAME_URL_SEARCH+"/todos")
    public ResponseEntity<List<Livros>> findAll(){
        List<Livros> livros = livrosService.findAll();
        return ResponseEntity.ok().body(livros);
    }
}
