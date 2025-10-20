package com.alexandre.userapi.models.dto;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.alexandre.userapi.models.User;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    @Id
    private String id;
    @NotBlank (message = "Nome é obrigatório")
    private String nome;
    @NotBlank (message = "CPF é obrigatório!")
    private String cpf;
    private String endereco;
    @NotBlank (message = "Email é obrigatório!")
    private String email;
    private String telefone;
    private LocalDateTime dataCadastro;

    public static UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setNome(user.getNome());
        userDto.setCpf(user.getCpf());
        userDto.setEndereco(user.getEndereco());
        userDto.setEmail(user.getEmail());
        userDto.setTelefone(user.getTelefone());
        userDto.setDataCadastro(user.getDataCadastro());
        return userDto;
    }
}
