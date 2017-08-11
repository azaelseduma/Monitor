package com.monitor.controller.rest.about;

import com.monitor.ApplicationTest;
import com.monitor.model.about.ContactUs;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Azael on 2017/08/11.
 */
public class AboutRestControllerTest extends ApplicationTest {

    @Test
    public void testContactUsList() {
        String url = "http://localhost:8080/rest/contactUs/list";

        HttpEntity<?> httpEntity = new HttpEntity<>(getHttpHeaders("aseduma@gmail.com", "xbox360"));

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ContactUs[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ContactUs[].class);

        System.out.println(responseEntity.getStatusCode());

        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            List<ContactUs> contactUsList = Arrays.asList(responseEntity.getBody());
            for (ContactUs contactUs : contactUsList) {
                System.out.println("Id: " + contactUs.getId());
                System.out.println("Name: " + contactUs.getName());
                System.out.println("Surname: " + contactUs.getSurname());
                System.out.println("Email: " + contactUs.getEmail());
                System.out.println("Message: " + contactUs.getMessage());
                System.out.println("Phone Number: " + contactUs.getPhoneNumber());
                System.out.println("Time Stamp: " + contactUs.getTimestamp());
                System.out.println("------------");
            }
        } else {
            System.out.println(responseEntity.getStatusCode());
        }
    }

    @Test
    public void testContactUsSave() {
        String url = "http://localhost:8080/rest/contactUs/save";

        ContactUs contactUs = new ContactUs();
        contactUs.setName("Azael");
        contactUs.setSurname("Seduma");
        contactUs.setEmail("aseduma@gmail.com");
        contactUs.setPhoneNumber("0743519649");
        contactUs.setMessage("Test " + (1 + new Random().nextInt(1000)));

        HttpEntity<ContactUs> httpEntity = new HttpEntity<>(contactUs, getHttpHeaders("aseduma@gmail.com", "xbox360"));

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Void.class);
        System.out.println(responseEntity.getStatusCode());
        if (responseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
            System.out.println("Saved");
        } else {
            System.out.println(responseEntity.getStatusCode());
        }
    }

    private HttpHeaders getHttpHeaders(String username, String password) {
        String credentials = username + ":" + password;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic " + new String(Base64.encodeBase64(credentials.getBytes())));
        return headers;
    }
}
