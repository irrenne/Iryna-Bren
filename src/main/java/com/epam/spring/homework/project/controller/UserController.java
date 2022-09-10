package com.epam.spring.homework.project.controller;

import com.epam.spring.homework.project.dto.UserDto;
import com.epam.spring.homework.project.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ApiOperation("Get user")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{login}")
    public UserDto getUser(@PathVariable String login) {
        return userService.getUser(login);
    }

    @ApiOperation("Create user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        return userService.createUser(userDto);
    }

    @ApiOperation("Update user")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{login}")
    public UserDto updateUser(@PathVariable String login, @RequestBody @Valid UserDto userDto) {
        return userService.updateUser(login, userDto);
    }

    @ApiOperation("Get users")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @ApiOperation("Delete user")
    @DeleteMapping(value = "/{login}")
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
        userService.deleteUser(login);
        return ResponseEntity.noContent().build();
    }
}
