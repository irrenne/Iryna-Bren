package com.epam.spring.homework.project.dto;

import com.epam.spring.homework.project.validation.ValidPassword;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    @JsonProperty(access = READ_ONLY)
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
}
