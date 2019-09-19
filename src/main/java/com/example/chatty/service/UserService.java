package com.example.chatty.service;

import com.example.chatty.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
    List<User> getAllusers();
}
