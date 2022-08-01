package com.epam.spring.homework.project.service.impl;

import com.epam.spring.homework.project.dto.UserDto;
import com.epam.spring.homework.project.exception.UserNotFoundException;
import com.epam.spring.homework.project.mapping.UserMapper;
import com.epam.spring.homework.project.model.Role;
import com.epam.spring.homework.project.model.User;
import com.epam.spring.homework.project.repository.UserRepository;
import com.epam.spring.homework.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto getUser(String login) {
        log.info("getUser by login {}", login);
        User user = userRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException("User is not found"));
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("createUser with login {}", userDto.getLogin());
        User user = UserMapper.INSTANCE.mapUserDtoToUser(userDto);
        user.setRole(Role.CLIENT);
        user = userRepository.save(user);
        log.info("USER IS CREATED");
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }

    @Transactional
    @Override
    public UserDto updateUser(String login, UserDto userDto) {
        log.info("updateUser with login {}", userDto.getLogin());
        User persistedUser = userRepository.findByLogin(login).orElseThrow(()
                -> new UserNotFoundException("User is not found"));
        persistedUser = UserMapper.INSTANCE.populateUserWithDtoFields(persistedUser, userDto);
        persistedUser = userRepository.save(persistedUser);
        return UserMapper.INSTANCE.mapUserToUserDto(persistedUser);
    }

    @Override
    public void deleteUser(String login) {
        log.info("deleteUser with login {}", login);
        User user = userRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException("User is not found"));
        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> getUsers(int page, int size) {
        log.info("getUsers");
        Pageable pageable = PageRequest.of(page, size, Sort.by("name", "surname").ascending());
        List<User> users = userRepository.findAllByRole(Role.CLIENT, pageable);
        return UserMapper.INSTANCE.usersToDto(users);
    }
}
