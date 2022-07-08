package com.epam.spring.homework.project.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String repeatPassword;
}
