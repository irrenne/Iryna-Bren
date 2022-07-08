package com.epam.spring.homework.project.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
}
