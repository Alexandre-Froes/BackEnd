package com.iftm.start_example.models.dto;

import java.io.Serializable;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

import com.iftm.start_example.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserDto implements Serializable {
    private String id;
    private String name;
    private int age;
    private Address address;

    public UserDto(User user) {
        this.id = user.getId().toString();
        this.name = user.getName();
        this.age = user.getAge();
        this.address = user.getAddress();
    }

    public UserDto(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
