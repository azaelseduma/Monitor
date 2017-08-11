package com.monitor.service.about;

import com.monitor.model.about.ContactUs;
import com.monitor.repository.about.ContactUsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Azael on 2017/08/10.
 */
@Service("contactUsService")
public class ContactUsServiceImpl implements ContactUsService {

    @Autowired
    private ContactUsRepository contactUsRepository;

    @Override
    public List<ContactUs> getAll() {
        return contactUsRepository.findAll();
    }

    @Override
    public void save(ContactUs contactUs) {
        contactUsRepository.save(contactUs);
    }
}
