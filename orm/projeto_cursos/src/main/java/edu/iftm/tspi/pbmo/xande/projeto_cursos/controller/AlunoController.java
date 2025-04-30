package edu.iftm.tspi.pbmo.xande.projeto_cursos.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import edu.iftm.tspi.pbmo.xande.projeto_cursos.domain.Curso;
import edu.iftm.tspi.pbmo.xande.projeto_cursos.dto.AlunoDTO;
// import edu.iftm.tspi.pbmo.xande.projeto_cursos.dto.CursoDTO;
import edu.iftm.tspi.pbmo.xande.projeto_cursos.exception.ConflitoDeDadosException;
import edu.iftm.tspi.pbmo.xande.projeto_cursos.exception.RecursoNaoEncontradoException;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    private ArrayList<AlunoDTO> alunos = new ArrayList<>();

    @GetMapping
    public ResponseEntity<ArrayList<AlunoDTO>> listaralunos() {
        return ResponseEntity.ok().body(alunos);
    }

    @PostMapping("")
    public ResponseEntity<Object> novo(@RequestBody AlunoDTO novoAluno) {
        boolean existe = alunos.stream()
                            .anyMatch(aluno -> aluno.getRa()
                            .equals(novoAluno.getRa()));

        if(existe) {
            throw new ConflitoDeDadosException("Já existe aluno com esse ra");
        }
        if(novoAluno.getNome() == null || novoAluno.getNome().equals("")) {
            throw new RecursoNaoEncontradoException("Não foi informado um nome do aluno");
        }
        alunos.add(novoAluno);
        return ResponseEntity.status(HttpStatus.CREATED)
                            .body(novoAluno);
    }

    @GetMapping("/{sigla}")
    public ResponseEntity<AlunoDTO> buscarPorRa(@PathVariable String ra) {
        for(AlunoDTO aluno : alunos) {
            if (aluno.getRa().equalsIgnoreCase(ra)) {
                return ResponseEntity.ok().body(aluno);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{sigla}")
    public ResponseEntity<Object> atualizar
    (@PathVariable String sigla, @RequestBody AlunoDTO alunoAtualizado) {
        for(AlunoDTO aluno : alunos) {
            if (aluno.getRa().equalsIgnoreCase(sigla)) {
                aluno.setNome(alunoAtualizado.getNome());
                return ResponseEntity.ok().body(alunoAtualizado);
            }
        }

        throw new RecursoNaoEncontradoException("Não foi encontrado o aluno");
    }

    @DeleteMapping("/{sigla}")
    public ResponseEntity<Object> excluir(@PathVariable String sigla) {
        boolean removido = alunos.removeIf(curso -> curso.getRa().equals(sigla));
        if(removido) {
            return ResponseEntity.noContent().build();
        }

        throw new RecursoNaoEncontradoException("Não foi informado um aluno com esse ra");
    }
}
