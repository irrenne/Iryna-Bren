package com.epam.spring.homework.project.service;

import com.epam.spring.homework.project.dto.UserDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserDto getUser(String login);

    UserDto createUser(UserDto user);

    UserDto updateUser(String login, UserDto user);

    void deleteUser(String login);

    List<UserDto> getUsers(int page, int size);
}
