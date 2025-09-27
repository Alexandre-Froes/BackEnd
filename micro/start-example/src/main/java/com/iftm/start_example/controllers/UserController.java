package com.iftm.start_example.controllers;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.iftm.start_example.models.User;
import com.iftm.start_example.models.dto.UserDto;
import com.iftm.start_example.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService service = new UserService();

    @GetMapping
    public ResponseEntity<List<UserDto>>findAll() {
        return service.findAll();
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<UserDto> findById
        (@PathVariable("id") ObjectId id) {
        return service.findById(id);
    }

    @PostMapping()
    public ResponseEntity<UserDto> create
        (@RequestBody User user) {
        return service.save(user);
    }
    
    @PutMapping
    public ResponseEntity<UserDto> update
        (@RequestBody UserDto user) {
        return service.update(user);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Object> delete
        (@PathVariable("id") ObjectId id) {
        return service.delete(id);
    }
}
