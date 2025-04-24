package edu.iftm.tspi.pbmo.xande.projeto_cursos.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.iftm.tspi.pbmo.xande.projeto_cursos.domain.Curso;
import edu.iftm.tspi.pbmo.xande.projeto_cursos.dto.CursoDTO;
import edu.iftm.tspi.pbmo.xande.projeto_cursos.dto.ErroDTO;
import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/cursos")
public class CursoController {
    private ArrayList<CursoDTO> cursos = new ArrayList<>();

    @GetMapping
    public ResponseEntity<ArrayList<CursoDTO>> listarCursos() {
        return ResponseEntity.ok().body(cursos);
    }

    @PostMapping("")
    public ResponseEntity<Object> novo(@RequestBody CursoDTO novoCurso) {
        boolean existe = cursos.stream()
                            .anyMatch(curso -> curso.getSigla()
                            .equals(novoCurso.getSigla()));

        if(existe) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ErroDTO.builder()
                .msg("Já existe curso com essa sigla.")
                .data(LocalDateTime.now()));
        }
        if(novoCurso.getNome() == null || novoCurso.getNome().equals("")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ErroDTO.builder()
                .msg("Não foi informado o nome do curso.")
                .data(LocalDateTime.now()));
        }
        cursos.add(novoCurso);
        return ResponseEntity.status(HttpStatus.CREATED)
                            .body(novoCurso);
    }

    @GetMapping("/{sigla}")
    public ResponseEntity<CursoDTO> buscarPorSigla(@PathVariable String sigla) {
        for(CursoDTO curso : cursos) {
            if (curso.getSigla().equalsIgnoreCase(sigla)) {
                return ResponseEntity.ok().body(curso);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{sigla}")
    public ResponseEntity<Object> atualizar
    (@PathVariable String sigla, @RequestBody Curso cursoAtualizado) {
        for(CursoDTO curso : cursos) {
            if (curso.getSigla().equalsIgnoreCase(sigla)) {
                curso.setNome(cursoAtualizado.getNome());
                return ResponseEntity.ok().body(cursoAtualizado);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ErroDTO.builder()
            .msg("Não existe contato com esse nome")
            .data(LocalDateTime.now()));
        
    }
}
