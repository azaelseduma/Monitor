package com.monitor.service.about;

import com.monitor.model.about.ContactUs;

import java.util.List;

/**
 * Created by Azael on 2017/08/10.
 */
public interface ContactUsService {
    void save(ContactUs contactUs);

    List<ContactUs> getAll();
}
