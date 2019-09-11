package com.example.chatty.service;

public interface SecurityService {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
}
