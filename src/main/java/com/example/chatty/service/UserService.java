package com.example.chatty.service;

import com.example.chatty.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
