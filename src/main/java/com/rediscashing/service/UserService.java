package com.rediscashing.service;

import com.rediscashing.entity.User;

public interface UserService {
    User getUser(int id);

    User saveUser(User user);

    String deleteUser(int id);
}
