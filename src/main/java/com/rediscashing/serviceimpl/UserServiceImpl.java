package com.rediscashing.serviceimpl;

import com.rediscashing.entity.User;
import com.rediscashing.repository.UserRepository;
import com.rediscashing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final String REDIS_CACHE_VALUE = "USER";

    @Autowired
    UserRepository repository;

    @Override
    @Cacheable(value = REDIS_CACHE_VALUE, key = "#id")
    public User getUser(int id) {
        Optional<User> user = repository.findById(id);
         if (user.isPresent()) {
             return user.get();
         }
         else {
             return new User();
         }
    }

    @Override
    @CachePut(value = REDIS_CACHE_VALUE, key = "#user.id")
    public User saveUser(User user) {
         return repository.save(user);
    }

    @Override
    @CacheEvict(value = REDIS_CACHE_VALUE, key = "#id")
    public String deleteUser(int id) {
        repository.deleteById(id);
        return "USER DELETED";
    }


}
