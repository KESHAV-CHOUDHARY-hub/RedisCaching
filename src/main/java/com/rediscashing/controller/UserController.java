package com.rediscashing.controller;

import com.rediscashing.entity.User;
import com.rediscashing.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl service;

    @GetMapping("users")
    public User getUser(@RequestParam int id) {
        return service.getUser(id);
    }

    @PostMapping("users")
    public String saveUser(@RequestBody User user) {
        service.saveUser(user);
        return "USER SAVED";
    }

    @DeleteMapping("users")
    public String deleteUser(@RequestParam int id) {
        return service.deleteUser(id);
    }
}
