package com.epam.spring.homework.project.repository;

import com.epam.spring.homework.project.model.User;

import java.util.List;

public interface UserRepository {
    User getUser(String login);

    User createUser(User user);

    User updateUser(String login, User user);

    void deleteUser(String login);

    List<User> getUsers();
}
