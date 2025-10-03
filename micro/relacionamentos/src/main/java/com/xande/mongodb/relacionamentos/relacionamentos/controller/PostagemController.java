package com.xande.mongodb.relacionamentos.relacionamentos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xande.mongodb.relacionamentos.relacionamentos.models.Postagem;
import com.xande.mongodb.relacionamentos.relacionamentos.repository.*;


@RestController
@RequestMapping("/postagens")
public class PostagemController {
    @Autowired
    private PostagemRepository postagemRepository;

    @GetMapping
    public List<Postagem> getAll() {
        return postagemRepository.findAll();
    }

    @PostMapping
    public Postagem salvar(@RequestBody Postagem postagem) {
        return postagemRepository.save(postagem);
    }

    @GetMapping("/{id}")
    public Object getPostagemById(@PathVariable("id") String id) {
        return postagemRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePostagemById(@PathVariable("id") String id) {
        postagemRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Object updatePostagemById(@PathVariable String id, @RequestBody Postagem postagem) {
        postagem.setId(id);
        return postagemRepository.save(postagem);
    }


}
