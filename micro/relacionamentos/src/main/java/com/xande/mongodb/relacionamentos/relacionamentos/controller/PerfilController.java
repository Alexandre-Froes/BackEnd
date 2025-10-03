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

import com.xande.mongodb.relacionamentos.relacionamentos.models.Perfil;
import com.xande.mongodb.relacionamentos.relacionamentos.repository.PerfilRepository;

@RestController
@RequestMapping("/perfis")
public class PerfilController {
    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping
    public List<Perfil> getAll() {
        return perfilRepository.findAll();
    }

    @PostMapping
    public Perfil salvar(@RequestBody Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    @GetMapping("/{id}")
    public Object getPerfilById(@PathVariable("id") String id) {
        return perfilRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePerfilById(@PathVariable("id") String id) {
        perfilRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Object updatePerfilById(@PathVariable String id, @RequestBody Perfil perfil) {
        perfil.setId(id);
        return perfilRepository.save(perfil);
    }
}
