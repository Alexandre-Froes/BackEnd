package com.xande.mongodb.relacionamentos.relacionamentos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xande.mongodb.relacionamentos.relacionamentos.models.*;
import com.xande.mongodb.relacionamentos.relacionamentos.repository.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PostagemRepository postagemRepository;

    @GetMapping
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario) {
        if(usuario.getPerfil() != null && usuario.getPerfil().getId() == null) {
            Perfil perfilSalvo = perfilRepository.save(usuario.getPerfil());
            usuario.setPerfil(perfilSalvo);
        }

        if(usuario.getPostagens() != null && !usuario.getPostagens().isEmpty()) {
            usuario.getPostagens().forEach(postagem -> {
                if(postagem.getId() == null) {
                    Postagem postagemSalva = postagemRepository.save(postagem);
                    postagem.setId(postagemSalva.getId());
                }
            });
        }

        return usuarioRepository.save(usuario);
    }

    @GetMapping("/{id}")
    public Object getUsuarioById(@PathVariable("id") String id) {
        return usuarioRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuarioById(@PathVariable("id") String id) {
        usuarioRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Object updateUsuarioById(@PathVariable String id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }
}