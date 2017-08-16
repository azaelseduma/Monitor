package com.monitor.controller.rest.about;

import com.monitor.model.about.ContactUs;
import com.monitor.service.about.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Azael on 2017/08/10.
 */
@RestController
public class AboutRestController {
    @Autowired
    private ContactUsService contactUsService;


    @RequestMapping(value = "rest/contactUs/", method = RequestMethod.GET)
    public ResponseEntity<List<ContactUs>> getAll() {
        List<ContactUs> contactUsList = contactUsService.getAll();
        if (contactUsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contactUsList, HttpStatus.OK);
    }

    @RequestMapping(value = "rest/contactUs/save", method = RequestMethod.POST)
    public ResponseEntity<Void> saveContactUs(@RequestBody ContactUs contactUs) {
        System.out.println("Creating Contact Us for " + contactUs.getEmail());
        try {
            contactUsService.save(contactUs);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
