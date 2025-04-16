package org.example.springdoc.service;

import org.example.springdoc.domain.User;

public interface UserService {
    boolean queryUser(String userName, String password);

    User queryUserByUserName(String userName);
}
