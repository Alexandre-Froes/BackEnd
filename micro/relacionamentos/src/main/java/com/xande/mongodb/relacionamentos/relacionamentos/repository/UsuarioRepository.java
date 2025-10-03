package com.xande.mongodb.relacionamentos.relacionamentos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.xande.mongodb.relacionamentos.relacionamentos.models.*;

public interface UsuarioRepository extends MongoRepository<Usuario, String>{
    
}
