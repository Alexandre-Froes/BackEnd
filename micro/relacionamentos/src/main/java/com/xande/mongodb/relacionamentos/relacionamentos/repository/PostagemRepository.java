package com.xande.mongodb.relacionamentos.relacionamentos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xande.mongodb.relacionamentos.relacionamentos.models.*;

public interface PostagemRepository extends MongoRepository<Postagem, String> {
    
}
