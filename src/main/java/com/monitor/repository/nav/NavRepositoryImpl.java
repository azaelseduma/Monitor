package com.monitor.repository.nav;

import com.monitor.model.nav.Nav;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Azael on 2017/08/10.
 */
@Repository("navRepository")
@Transactional(value = "transactionManager")
public class NavRepositoryImpl implements NavRepository {
    @Autowired
    @Qualifier(value = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public List<Nav> findAllEnabled() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Nav where enabled = :enabled")
                .setParameter("enabled", true).list();
    }
}
