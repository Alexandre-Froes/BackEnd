package com.iftm.start_example.services;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.iftm.start_example.models.dto.UserDto;
import com.iftm.start_example.repositories.UserRepository;

public class UserService {
    @Autowired
    private UserRepository repository;

    public ResponseEntity<List<UserDto>> findAll() {
        var dbUsers = repository.findAll();

        if(dbUsers.isEmpty())
            return ResponseEntity.notFound().build();

        var usersDtos = dbUsers.stream().map(user -> {
            var userDTO = new UserDto(user);
            return userDTO;
        }).collect((Collectors.toList()));

        return ResponseEntity.ok(usersDtos);
    }

    public ResponseEntity<UserDto> findById(ObjectId id) {
        if(id == null)
            return ResponseEntity.badRequest().build();

        var dbUser = repository.findById(id);

        if(dbUser.isEmpty())
            return ResponseEntity.notFound().build();
        

        return ResponseEntity.ok(new UserDto(dbUser.get()));
    }
}


