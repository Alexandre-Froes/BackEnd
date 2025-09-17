package com.iftm.start_example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iftm.start_example.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;

import com.iftm.start_example.models.dto.UserDto;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping()
    public ResponseEntity<List<UserDto>>findAll() {
        return service.findAll();
    }
    

}
