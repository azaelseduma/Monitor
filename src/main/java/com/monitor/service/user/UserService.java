package com.monitor.service.user;

import com.monitor.model.user.User;

import java.util.List;

/**
 * Created by Azael on 2017/08/10.
 */
public interface UserService {
    User getByEmail(String email);

    void save(User user);

    void delete(User user);

    void update(User user);

    List<User> getAll();

    User getByNameAndSurname(String name, String surname);

    boolean isUserExist(User user);

    boolean isUserExist(String email);
}
