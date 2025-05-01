package edu.iftm.tspi.pbmo.xande.projeto_cursos.controller;

import java.util.ArrayList;
import java.util.List;

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

import edu.iftm.tspi.pbmo.xande.projeto_cursos.dto.AlunoDTO;
import edu.iftm.tspi.pbmo.xande.projeto_cursos.dto.CursoDTO;
import edu.iftm.tspi.pbmo.xande.projeto_cursos.exception.ConflitoDeDadosException;
import edu.iftm.tspi.pbmo.xande.projeto_cursos.exception.RecursoNaoEncontradoException;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    private ArrayList<CursoDTO> cursos = new ArrayList<>();
    private AlunoController alunoController = new AlunoController();

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
            throw new ConflitoDeDadosException("Já existe curso com essa sigla.");
        }

        if(novoCurso.getNome() == null || novoCurso.getNome().equals("")) {
            throw new RecursoNaoEncontradoException("Não foi informado o nome do curso.");
        }
        cursos.add(novoCurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCurso);
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
    (@PathVariable String sigla, @RequestBody CursoDTO cursoAtualizado) {
        for(CursoDTO curso : cursos) {
            if (curso.getSigla().equalsIgnoreCase(sigla)) {
                curso.setNome(cursoAtualizado.getNome());
                return ResponseEntity.ok().body(cursoAtualizado);
            }
        }

        throw new RecursoNaoEncontradoException("Não existe curso com essa sigla.");
        
    }

    @DeleteMapping("/{sigla}")
    public ResponseEntity<Object> excluir(@PathVariable String sigla) {
        boolean vazio = cursos.isEmpty();
        boolean removido = cursos.removeIf(curso -> curso.getSigla().equals(sigla));

        if(removido) {
            return ResponseEntity.noContent().build();
        } else if (!vazio) {
            throw new ConflitoDeDadosException("Existem alunos nesse curso, não é possível excluir.");
        }

            throw new RecursoNaoEncontradoException("Não existe curso com essa sigla.");
    }

    @RequestMapping("/{sigla}/alunos")
    public ResponseEntity<List<AlunoDTO>> listarAlunoPorCurso(@PathVariable String sigla) {
        ArrayList<AlunoDTO> alunos = alunoController.getListaAlunos();
        ArrayList<AlunoDTO> alunosCorretos = new ArrayList<>();

        AlunoDTO alunoTeste = new AlunoDTO("t1", "alunoTeste", "emailTeste", "123");
        alunos.add(alunoTeste);
    
        for(AlunoDTO aluno : alunos) {
            if(aluno.getCursoSigla().equalsIgnoreCase(sigla))
                alunosCorretos.add(aluno);
            
        }

        return ResponseEntity.ok(alunosCorretos);
    }
}
