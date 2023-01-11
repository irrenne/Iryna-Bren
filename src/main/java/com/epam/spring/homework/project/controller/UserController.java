package com.epam.spring.homework.project.controller;

import com.epam.spring.homework.project.api.UserApi;
import com.epam.spring.homework.project.dto.UserDto;
import com.epam.spring.homework.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;

    @Override
    public UserDto getUser(String login) {
        return userService.getUser(login);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        return userService.createUser(userDto);
    }

    @Override
    public UserDto updateUser(String login, UserDto userDto) {
        return userService.updateUser(login, userDto);
    }

    @Override
    public List<UserDto> getUsers(int page, int size) {
        return userService.getUsers(page, size);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String login) {
        userService.deleteUser(login);
        return ResponseEntity.noContent().build();
    }
}
