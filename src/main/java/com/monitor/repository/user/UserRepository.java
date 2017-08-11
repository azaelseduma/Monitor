package com.monitor.repository.user;

import com.monitor.model.user.User;

import java.util.List;

/**
 * Created by Azael on 2017/08/10.
 */
public interface UserRepository {
    User findByEmail(String email);

    List<User> findAll();

    void update(User user);

    void delete(User user);

    void save(User user);

    User findByNameAndSurname(String name, String surname);
}
