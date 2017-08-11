package com.monitor.repository.about;

import com.monitor.model.about.ContactUs;

import java.util.List;

/**
 * Created by Azael on 2017/08/10.
 */
public interface ContactUsRepository {
    void save(ContactUs contactUs);

    List<ContactUs> findAll();
}
