package com.monitor.repository.user;

import com.monitor.model.user.UserRole;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Azael on 2017/08/10.
 */
@Repository("userRoleRepository")
@Transactional(value = "transactionManager")
public class UserRoleRepositoryImpl implements UserRoleRepository {

    @Autowired
    @Qualifier(value = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void save(UserRole userRole) {
        sessionFactory.getCurrentSession().save(userRole);
    }

    @Override
    public void delete(UserRole userRole) {
        sessionFactory.getCurrentSession().update(userRole);
    }

}
