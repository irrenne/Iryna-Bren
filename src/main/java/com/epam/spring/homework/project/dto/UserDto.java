package com.epam.spring.homework.project.dto;

import com.epam.spring.homework.project.model.Role;
import com.epam.spring.homework.project.validation.ValidPassword;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Long id;

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotBlank(message = "surname is mandatory")
    private String surname;

    @NotBlank(message = "login is mandatory")
    private String login;

    @ValidPassword
    @NotBlank(message = "password is mandatory")
    private String password;
    private Role role;
}
