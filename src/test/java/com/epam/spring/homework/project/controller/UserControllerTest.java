package com.epam.spring.homework.project.controller;

import com.epam.spring.homework.project.dto.UserDto;
import com.epam.spring.homework.project.exception.UserNotFoundException;
import com.epam.spring.homework.project.service.UserService;
import com.epam.spring.homework.project.test.config.TestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static com.epam.spring.homework.project.test.util.TestDataUtil.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@Import(TestConfig.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final static String USERS_API = "/api/v1/users";

    @Test
    void getUserTest() throws Exception {
        UserDto userDto = createUserDto();

        when(userService.getUser(LOGIN)).thenReturn(userDto);

        mockMvc.perform(get(USERS_API + "/" + LOGIN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.login").value(LOGIN));
    }

    @Test
    void createUserTest() throws Exception {
        UserDto userDto = createUserDto();

        when(userService.createUser(userDto)).thenReturn(userDto);

        mockMvc.perform(post(USERS_API)
                        .content(objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.login").value(LOGIN));
    }

    @Test
    void createNotValidUserTest() throws Exception {
        UserDto userDto = createUserDto();
        userDto.setName(EMPTY_FIELD);
        userDto.setSurname(EMPTY_FIELD);
        userDto.setLogin(EMPTY_FIELD);
        userDto.setPassword(NOT_VALID_PASSWORD);

        when(userService.createUser(userDto)).thenReturn(userDto);

        mockMvc.perform(post(USERS_API)
                        .content(objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateUserTest() throws Exception {
        UserDto userDto = createUserDto();

        when(userService.updateUser(LOGIN, userDto)).thenReturn(userDto);

        mockMvc.perform(put(USERS_API + "/" + LOGIN)
                        .content(objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.login").value(LOGIN));
    }

    @Test
    void updateNotValidUserTest() throws Exception {
        UserDto userDto = createUserDto();
        userDto.setName(EMPTY_FIELD);
        userDto.setSurname(EMPTY_FIELD);
        userDto.setLogin(EMPTY_FIELD);
        userDto.setPassword(NOT_VALID_PASSWORD);

        when(userService.updateUser(LOGIN, userDto)).thenReturn(userDto);

        mockMvc.perform(put(USERS_API + "/" + LOGIN)
                        .content(objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateNonExistingUserTest() throws Exception {
        UserDto userDto = createUserDto();

        when(userService.updateUser(LOGIN, userDto)).thenThrow(new UserNotFoundException("User is not found"));

        mockMvc.perform(put(USERS_API + "/" + LOGIN)
                        .content(objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getUsersTest() throws Exception {
        UserDto userDto = createUserDto();

        when(userService.getUsers(anyInt(), anyInt())).thenReturn(Collections.singletonList(userDto));

        mockMvc.perform(get(USERS_API + "/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].login").value(LOGIN));
    }

    @Test
    void deleteUserTest() throws Exception {
        mockMvc.perform(delete(USERS_API + "/" + LOGIN))
                .andExpect(status().isNoContent());

        verify(userService).deleteUser(LOGIN);
    }
}
