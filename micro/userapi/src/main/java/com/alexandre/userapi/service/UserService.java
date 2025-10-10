package com.alexandre.userapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alexandre.userapi.models.User;
import com.alexandre.userapi.models.dto.UserDto;
import com.alexandre.userapi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDto> getAll() {
        List<User> usuarios = userRepository.findAll();
        return usuarios.stream()
                .map(UserDto::convert)
                .collect(Collectors.toList());
    }

    public UserDto findById(String userId) {
    User usuario = userRepository.findById(userId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    return UserDto.convert(usuario);
    }

    public UserDto save(UserDto userDto) {
        userDto.setDataCadastro(LocalDateTime.now());
        User user = userRepository.save(User.convert(userDto));
        return UserDto.convert(user);
    }

    public UserDto delete(String userId) {
        User user = userRepository
                .findById(userId).orElseThrow(() -> new RuntimeException());

        userRepository.delete(user);
        return UserDto.convert(user);
    }

    public UserDto findByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf);
        if (user != null) {
            return UserDto.convert(user);
        }

        return null;
    }

    public List<UserDto> queryByName(String nome) {
        List<User> usuarios = userRepository.queryByNomeLike(nome);

        return usuarios
                .stream()
                .map(UserDto::convert)
                .collect(Collectors.toList());
    }

    public Page<UserDto> getAllPage(Pageable page) {
        Page<User> users = userRepository.findAll(page);
        return users
            .map(UserDto::convert);
    }

    public UserDto editUser(String userId, UserDto userDto) {
        User user = userRepository
            .findById(userId).orElseThrow(() -> new RuntimeException());
        if(userDto.getEmail() != null &&
                !user.getEmail().equals(userDto.getEmail())) {
            
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getEndereco() != null &&
                !user.getEndereco().equals(userDto.getEndereco())) {

        user.setEndereco(userDto.getEndereco());
        }
        if (userDto.getTelefone() != null &&
                !user.getTelefone().equals(userDto.getTelefone())) {

            user.setTelefone(userDto.getTelefone());
        }
        user = userRepository.save(user);
        return UserDto.convert(user);
    }
}
