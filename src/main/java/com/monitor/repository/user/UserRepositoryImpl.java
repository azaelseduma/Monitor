package com.monitor.repository.user;

import com.monitor.model.user.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Azael on 2017/08/10.
 */
@Repository("userRepository")
@Transactional(value = "transactionManager")
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    @Qualifier(value = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public User findByEmail(String email) {
        List<User> users = sessionFactory.getCurrentSession()
                .createQuery("from User where email = :email")
                .setParameter("email", email).list();
        return users != null && users.size() > 0 ? users.get(0) : null;
    }

    @Override
    public User findByNameAndSurname(String name, String surname) {
        List<User> users = sessionFactory.getCurrentSession()
                .createQuery("from User where name = :name and surname = :surname")
                .setParameter("name", name)
                .setParameter("surname", surname)
                .list();
        return users != null && users.size() > 0 ? users.get(0) : null;
    }

    @Override
    public List<User> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }
}
