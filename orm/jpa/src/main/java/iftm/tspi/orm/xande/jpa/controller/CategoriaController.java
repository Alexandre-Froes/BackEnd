package iftm.tspi.orm.xande.jpa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iftm.tspi.orm.xande.jpa.domain.Categoria;
import iftm.tspi.orm.xande.jpa.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    // @GetMapping
    // public ResponseEntity<List<Categoria>> listar() {
    //     List<Categoria> categorias = repository.findAll();
    //     return ResponseEntity.ok(categorias);
    // }

    @GetMapping("{id}")
    public ResponseEntity<Categoria> listarPorId(@PathVariable Integer id) {
        Categoria categoria = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(
                                "Categoria com id " + id + " não encontrado"));

        return ResponseEntity.ok(categoria);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodos(
                            @RequestParam(required = false) String nome) {
        
            if (nome == null) {
                return ResponseEntity.ok(repository.findAll());
            }                    
            List<Categoria> categorias = repository.findByNomeContainingIgnoreCase(nome);

            if (categorias.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(categorias);
    }   
}
