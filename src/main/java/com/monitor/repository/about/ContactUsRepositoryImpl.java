package com.monitor.repository.about;

import com.monitor.model.about.ContactUs;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Azael on 2017/08/10.
 */
@Repository("contactUsRepository")
@Transactional(value = "transactionManager")
public class ContactUsRepositoryImpl implements ContactUsRepository {
    @Autowired
    @Qualifier(value = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public List<ContactUs> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from ContactUs").list();
    }

    @Override
    public void save(ContactUs contactUs) {
        sessionFactory.getCurrentSession().save(contactUs);
    }
}
