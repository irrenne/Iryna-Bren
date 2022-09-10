package com.epam.spring.homework.project.service.impl;

import com.epam.spring.homework.project.dto.UserDto;
import com.epam.spring.homework.project.mapping.UserMapper;
import com.epam.spring.homework.project.model.User;
import com.epam.spring.homework.project.repository.UserRepository;
import com.epam.spring.homework.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto getUser(String login) {
        log.info("getUser by login {}", login);
        User user = userRepository.getUser(login);
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("createUser with login {}", userDto.getLogin());
        User user = UserMapper.INSTANCE.mapUserDtoToUser(userDto);
        user = userRepository.createUser(user);
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }

    @Override
    public UserDto updateUser(String login, UserDto userDto) {
        log.info("updateUser with login {}", userDto.getLogin());
        User user = UserMapper.INSTANCE.mapUserDtoToUser(userDto);
        user = userRepository.updateUser(login, user);
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }

    @Override
    public void deleteUser(String login) {
        log.info("deleteUser with login {}", login);
        userRepository.deleteUser(login);
    }

    @Override
    public List<UserDto> getUsers() {
        log.info("getUsers");
        List<User> users = userRepository.getUsers();
        return UserMapper.INSTANCE.usersToDto(users);
    }
}
