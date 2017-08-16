package com.monitor.controller.rest.user;

import com.monitor.ApplicationTest;
import com.monitor.model.user.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Azael on 2017/08/11.
 */
public class UserRestControllerTest extends ApplicationTest {
    public static final String REST_SERVICE_URI = "http://localhost:8080/rest";
    public static final String USERNAME = "aseduma@gmail.com";
    public static final String PASSWORD = "xbox360";

    /*
     * Full test pack
     */
    @Test
    public void testUserRestControllerPack() {
        listAllUsers();
        getUser();

        createUser();
        listAllUsers();

        updateUser();
        listAllUsers();

        deleteUser();
        listAllUsers();
    }

    private HttpHeaders getHeaders() {
        String plainCredentials = USERNAME + ":" + PASSWORD;
        String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }

    /*
     * Send a GET request to get a specific user.
     */
    @Test
    public void listAllUsers() {
        try {
            HttpEntity<?> request = new HttpEntity<>(getHeaders());

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<UserResponse[]> responseEntity = restTemplate.exchange(REST_SERVICE_URI + "/user/", HttpMethod.GET, request, UserResponse[].class);

            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntity.getBody());

            if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
                List<UserResponse> userResponses = Arrays.asList(responseEntity.getBody());
                userResponses.forEach(UserResponse::print);
            } else {
                System.out.println(responseEntity.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUser() {
        try {
            System.out.println("\nTesting getUser API----------");
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> request = new HttpEntity<>(getHeaders());
            ResponseEntity<UserResponse> response = restTemplate.exchange(REST_SERVICE_URI + "/user/aseduma@gmail.com", HttpMethod.GET, request, UserResponse.class);
            System.out.println(response.getStatusCode());
            UserResponse userResponse = response.getBody();
            userResponse.print();
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }

    /*
     * Send a POST request to create a new user.
     */
    @Test
    public void createUser() {
        try {
            System.out.println("\nTesting create User API----------");
            RestTemplate restTemplate = new RestTemplate();
            User user = new User();
            user.setEmail("test@gmail.com");
            user.setName("tester");
            user.setSurname("tester");
            user.setPassword(new BCryptPasswordEncoder().encode("xbox360"));
            user.setActive(true);
            HttpEntity<Object> request = new HttpEntity<>(user, getHeaders());
            URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/user/save", request, User.class);
            System.out.println("Location : " + uri.toASCIIString());
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }

    /*
     * Send a PUT request to update an existing user.
     */
    @Test
    public void updateUser() {
        try {
            System.out.println("\nTesting update User API----------");
            RestTemplate restTemplate = new RestTemplate();
            User user = new User();
            user.setEmail("test@gmail.com");
            user.setName("testing");
            user.setSurname("testing");
            user.setPassword(new BCryptPasswordEncoder().encode("xbox360"));
            user.setActive(true);
            HttpEntity<Object> request = new HttpEntity<>(user, getHeaders());
            ResponseEntity<UserResponse> response = restTemplate.exchange(REST_SERVICE_URI + "/user/update/test@gmail.com", HttpMethod.PUT, request, UserResponse.class);
            System.out.println(response.getStatusCode());
            UserResponse userResponse = response.getBody();
            userResponse.print();
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }

    /*
     * Send a DELETE request to delete a specific user.
     */
    @Test
    public void deleteUser() {
        try {
            System.out.println("\nTesting delete User API----------");
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> request = new HttpEntity<>(getHeaders());
            ResponseEntity<UserResponse> response = restTemplate.exchange(REST_SERVICE_URI + "/user/delete/test@gmail.com", HttpMethod.DELETE, request, UserResponse.class);
            System.out.println(response.getStatusCode() + " " + response.getBody());
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }

}
