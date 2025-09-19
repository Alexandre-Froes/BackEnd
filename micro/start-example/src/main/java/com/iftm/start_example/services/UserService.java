package com.iftm.start_example.services;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iftm.start_example.models.User;
import com.iftm.start_example.models.dto.UserDto;
import com.iftm.start_example.repositories.UserRepository;

@Service
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

    public ResponseEntity<UserDto> save(User user) {
        if(user.getName().isBlank() || user.getAge() <= 0)
            return ResponseEntity.badRequest().build();

        user.setId(ObjectId.get());
        return ResponseEntity.ok(new UserDto(repository.save(user)));
    }

    public ResponseEntity<UserDto> update(UserDto user) {
        if(user.getId() == null)
            return ResponseEntity.badRequest().build();

        var objectId = new ObjectId(user.getId());
        var dbUser = repository.findById(objectId);
        if(dbUser.isEmpty()) 
            return ResponseEntity.badRequest().build();
        
        var dbUserObj = dbUser.get();
        dbUserObj.setName(user.getName());
        dbUserObj.setAge(user.getAge());
        return ResponseEntity.ok(new UserDto(repository.save(dbUserObj)));
    }

    public ResponseEntity<Object> delete(ObjectId id) {
        if(id == null)
            return ResponseEntity.badRequest().build();
        repository.deleteById(id);

        var dbUser = repository.findById(id);
        if(dbUser.isPresent())
            return ResponseEntity.status
                (HttpStatus.NOT_MODIFIED).build();
        
        return ResponseEntity.ok().build();
    }
}


