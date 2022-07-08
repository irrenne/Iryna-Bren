package com.epam.spring.homework.project.repository.impl;

import com.epam.spring.homework.project.exception.UserNotFoundException;
import com.epam.spring.homework.project.repository.UserRepository;
import com.epam.spring.homework.project.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {
    private final List<User> users = new ArrayList<>();

    @Override
    public User getUser(String login) {
        return users.stream().filter(user -> user.getLogin().equals(login))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User is not found"));
    }

    @Override
    public User createUser(User user) {
        users.add(user);
        return user;
    }

    @Override
    public User updateUser(String login, User user) {
        boolean isDeleted = users.removeIf(u -> u.getLogin().equals(login));
        if (isDeleted) {
            users.add(user);
        } else {
            throw new UserNotFoundException("User is not found");
        }
        return user;
    }

    @Override
    public void deleteUser(String login) {
        users.removeIf(u -> u.getLogin().equals(login));
    }

    @Override
    public List<User> getUsers() {
        return users;
    }
}
