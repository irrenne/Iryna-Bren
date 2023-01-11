package com.epam.spring.homework.project.service.impl;

import com.epam.spring.homework.project.dto.UserDto;
import com.epam.spring.homework.project.exception.UserNotFoundException;
import com.epam.spring.homework.project.model.User;
import com.epam.spring.homework.project.repository.UserRepository;
import com.epam.spring.homework.project.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.epam.spring.homework.project.test.util.TestDataUtil.LOGIN;
import static com.epam.spring.homework.project.test.util.TestDataUtil.OTHER_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void getUserByLoginTest() {
        User user = TestDataUtil.createUser();
        when(userRepository.findByLogin(LOGIN)).thenReturn(Optional.of(user));

        UserDto userDto = userService.getUser(LOGIN);

        assertThat(userDto, allOf(
                hasProperty("id", equalTo(user.getId())),
                hasProperty("login", equalTo(user.getLogin())),
                hasProperty("name", equalTo(user.getName())),
                hasProperty("surname", equalTo(user.getSurname()))
        ));
    }

    @Test
    void getUserNotFoundTest() {
        when(userRepository.findByLogin(LOGIN)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUser(LOGIN));
    }

    @Test
    void createUserTest() {
        User testUser = TestDataUtil.createUser();
        UserDto testUserDto = TestDataUtil.createUserDto();
        when(userRepository.save(any())).thenReturn(testUser);

        UserDto userDto = userService.createUser(testUserDto);

        assertThat(userDto, allOf(
                hasProperty("login", equalTo(testUserDto.getLogin())),
                hasProperty("name", equalTo(testUserDto.getName())),
                hasProperty("surname", equalTo(testUserDto.getSurname()))
        ));
    }

    @Test
    void updateUserTest() {
        User user = TestDataUtil.createUser();
        UserDto testUserDto = TestDataUtil.createUserDto();
        testUserDto.setName(OTHER_NAME);
        when(userRepository.findByLogin(LOGIN)).thenReturn(Optional.of(user));
        when(userRepository.save(any())).thenReturn(user);

        UserDto userDto = userService.updateUser(LOGIN, testUserDto);

        assertThat(userDto, allOf(
                hasProperty("login", equalTo(testUserDto.getLogin())),
                hasProperty("name", equalTo(testUserDto.getName())),
                hasProperty("surname", equalTo(testUserDto.getSurname()))
        ));
    }

    @Test
    void updateUserNotFoundTest() {
        UserDto testUserDto = TestDataUtil.createUserDto();
        when(userRepository.findByLogin(LOGIN)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(LOGIN, testUserDto));
    }

    @Test
    void deleteUserTest() {
        User user = TestDataUtil.createUser();
        when(userRepository.findByLogin(LOGIN)).thenReturn(Optional.of(user));

        userService.deleteUser(LOGIN);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void getUsersTest() {
        List<User> users = List.of(TestDataUtil.createUser());
        when(userRepository.findAllByRole(any(), any())).thenReturn(users);

        List<UserDto> foundUsers = userService.getUsers(0, 1);

        verify(userRepository).findAllByRole(any(), any());
        assertThat(foundUsers, hasSize(users.size()));
    }
}
