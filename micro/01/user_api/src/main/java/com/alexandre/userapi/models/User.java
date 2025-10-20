package com.alexandre.userapi.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.alexandre.userapi.models.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "user")
public class User {
    
    @Id
    private String id;
    @Field("name")
    private String nome;
    @Field("cpf")
    private String cpf;
    @Field("endereco")
    private String endereco;
    @Field("email")
    private String email;
    @Field("telefone")
    private String telefone;
    @Field("dataCadastro")
    private LocalDateTime dataCadastro;

    public static User convert(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setNome(userDto.getNome());
        user.setCpf(userDto.getCpf());
        user.setEndereco(userDto.getEndereco());
        user.setEmail(userDto.getEmail());
        user.setTelefone(userDto.getTelefone());
        user.setDataCadastro(userDto.getDataCadastro());
        return user;
    }
}
