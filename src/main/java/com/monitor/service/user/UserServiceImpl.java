package com.monitor.service.user;

import com.monitor.model.user.User;
import com.monitor.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Azael on 2017/08/10.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getByNameAndSurname(String name, String surname) {
        return userRepository.findByNameAndSurname(name, surname);
    }

    @Override
    public boolean isUserExist(User user) {
        return userRepository.findByEmail(user.getEmail()) != null;
    }

    @Override
    public boolean isUserExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
