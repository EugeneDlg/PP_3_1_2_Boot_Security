package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getByUsername(String username);
    Optional<User> getById(Integer id);

    List<User> getUsers();

    void addUser(User user);

    void deleteUser(Integer id);

    void updateUser(Integer id, User newUser);
}
