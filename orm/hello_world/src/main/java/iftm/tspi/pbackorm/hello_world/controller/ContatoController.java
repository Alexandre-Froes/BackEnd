package iftm.tspi.pbackorm.hello_world.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import iftm.tspi.pbackorm.hello_world.domain.Contato;
import iftm.tspi.pbackorm.hello_world.dta.ErroDTO;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/contatos")
public class ContatoController {
    private List<Contato> contatos = new ArrayList<>();
    
    @GetMapping
    public ResponseEntity<List<Contato>> getContatos(){
        return ResponseEntity.ok().body(contatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> buscarPorId(@PathVariable Integer id) {
        for(Contato contato : contatos) {
            if(contato.getCodigo().equals(id)) {
                return ResponseEntity.ok().body(contato);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Contato>> buscarPorNome(
        @RequestParam(required = true) String nome) {
        List<Contato> contatosEncontrados = new ArrayList<>();

        for(Contato contato : contatos) {
            if (contato.getNome().equalsIgnoreCase(nome.toLowerCase())) {
                contatosEncontrados.add(contato);
            }

        }
        return ResponseEntity.ok().body(contatosEncontrados);
    }

    @PostMapping("")
    public ResponseEntity<Object> novo(@RequestBody Contato novoContato) {
        boolean existe = contatos.stream()
                                .anyMatch(contato -> contato.getCodigo()
                                .equals(novoContato.getCodigo()));
        
        if(existe) {
            new ErroDTO("Já existe um contato com esse código.", 
                        novoContato.getCodigo(), 
                        LocalDateTime.now());

            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErroDTO("Já existe um contato com esse código.", 
                        novoContato.getCodigo(), 
                        LocalDateTime.now()));
        }
        
        contatos.add(novoContato);
        return ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body(novoContato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(
    @PathVariable Integer id, @RequestBody Contato contatoAtualizado) {
    for(Contato contato : contatos) {
        if(contato.getCodigo().equals(id)) {
            contato.setNome(contatoAtualizado.getNome());
            return ResponseEntity.ok(contatoAtualizado);
        }
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        new ErroDTO("Contato não encontrado", id, LocalDateTime.now()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Integer id) {
        boolean removido = contatos.removeIf(contato -> contato.getCodigo().equals(id));
        if(removido) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErroDTO("Contato não encontrado", id, LocalDateTime.now())
        );
    }
}
