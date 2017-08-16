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
    public static final String REST_SERVICE_URI = "http://localhost:8080/rest";
    public static final String USERNAME = "aseduma@gmail.com";
    public static final String PASSWORD = "xbox360";

    @Test
    public void listContactUsList() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ContactUs[]> responseEntity = restTemplate.exchange(REST_SERVICE_URI + "/contactUs/", HttpMethod.GET, httpEntity, ContactUs[].class);

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
    public void saveContactUs() {
        ContactUs contactUs = new ContactUs();
        contactUs.setName("Azael");
        contactUs.setSurname("Seduma");
        contactUs.setEmail("aseduma@gmail.com");
        contactUs.setPhoneNumber("0743519649");
        contactUs.setMessage("Test " + (1 + new Random().nextInt(1000)));

        HttpEntity<ContactUs> request = new HttpEntity<>(contactUs, getHeaders());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(REST_SERVICE_URI + "/save", HttpMethod.POST, request, Void.class);
        System.out.println(response.getStatusCode());
        if (response.getStatusCode().equals(HttpStatus.CREATED)) {
            System.out.println("Saved");
        }
    }

    private HttpHeaders getHeaders() {
        String plainCredentials = USERNAME + ":" + PASSWORD;
        String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }

}
