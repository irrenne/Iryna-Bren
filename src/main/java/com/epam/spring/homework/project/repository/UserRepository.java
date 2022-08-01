package com.epam.spring.homework.project.repository;

import com.epam.spring.homework.project.model.Role;
import com.epam.spring.homework.project.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    List<User> findAllByRole(Role role, Pageable pageable);
}
