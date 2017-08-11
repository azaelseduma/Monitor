package com.monitor.controller.rest.user;

import com.monitor.ApplicationTest;
import com.monitor.model.user.User;
import com.monitor.model.user.UserRole;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Azael on 2017/08/11.
 */
public class UserRestControllerTest extends ApplicationTest {
    @Test
    public void testUserList() {
        String url = "http://localhost:8080/rest/user/list";

        HttpEntity<?> httpEntity = new HttpEntity<>(getHttpHeaders("aseduma@gmail.com", "xbox360"));

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, User[].class);

        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            List<User> users = Arrays.asList(responseEntity.getBody());
            for (User user : users) {
                System.out.println("Name: " + user.getName());
                System.out.println("Surname: " + user.getSurname());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Active: " + user.isActive());
                System.out.println("Roles: ");
                for (UserRole userRole : user.getUserRoles()) {
                    System.out.print(userRole.getRole().getDescription() + "\t");
                }
                System.out.println("Time Stamp: " + user.getTimestamp());
                System.out.println("------------");
            }
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
