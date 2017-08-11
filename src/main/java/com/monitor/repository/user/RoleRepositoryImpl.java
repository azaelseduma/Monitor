package com.monitor.repository.user;

import com.monitor.model.user.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Azael on 2017/08/10.
 */
@Repository("roleRepository")
@Transactional(value = "transactionManager")
public class RoleRepositoryImpl implements RoleRepository {
    @Autowired
    @Qualifier(value = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public Role findByRole(String role) {
        List<Role> roles = sessionFactory.getCurrentSession()
                .createQuery("from Role where role = :role")
                .setParameter("role", role).list();
        return roles != null && roles.size() > 0 ? roles.get(0) : null;
    }
}
