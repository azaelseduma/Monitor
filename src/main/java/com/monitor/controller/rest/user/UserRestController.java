package com.monitor.controller.rest.user;

import com.monitor.model.user.User;
import com.monitor.service.user.UserRoleService;
import com.monitor.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Azael on 2017/08/10.
 */
@RestController
public class UserRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * @return
     */
    @RequestMapping(value = "/rest/user/", method = RequestMethod.GET)
    public ResponseEntity<List<UserResponse>> getAll() {
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<UserResponse> userResponses = users.stream().map(UserResponse::new).collect(Collectors.toList());
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    /**
     *
     * @param email
     * @return
     */
    @RequestMapping(value = "/rest/user/{email}", method = RequestMethod.GET)
    public ResponseEntity<UserResponse> getUser(@PathVariable("email") String email) {
        User user = userService.getByEmail(email);
        if (user == null) {
            System.out.println("User with email " + email + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserResponse userResponse = new UserResponse(user);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    /**
     *
     * @param name
     * @param surname
     * @return
     */
    @RequestMapping(value = "/rest/user/{name}/{surname}", method = RequestMethod.GET)
    public ResponseEntity<UserResponse> getUser(@PathVariable("name") String name, @PathVariable("surname") String surname) {
        User user = userService.getByNameAndSurname(name, surname);
        if (user == null) {
            System.out.println("User with name " + name + " and surname " + surname + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserResponse userResponse = new UserResponse(user);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    /**
     *
     * @param user
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/rest/user/save", method = RequestMethod.POST)
    public ResponseEntity<Void> saveUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {

        System.out.println("Creating User " + user.getEmail());

        if (userService.isUserExist(user)) {
            System.out.println("A User with email " + user.getEmail() + " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userService.save(user);
        userRoleService.save(user, "USER");
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{email}").buildAndExpand(user.getEmail()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     *
     * @param email
     * @param user
     * @return
     */
    @RequestMapping(value = "/rest/user/update/{email}", method = RequestMethod.PUT)
    public ResponseEntity<UserResponse> updateUser(@PathVariable("email") String email, @RequestBody User user) {
        System.out.println("Updating User " + email);

        User currentUser = userService.getByEmail(email);

        if (currentUser == null) {
            System.out.println("User with email " + email + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentUser.setName(user.getName());
        currentUser.setSurname(user.getSurname());
        currentUser.setPassword(user.getPassword());
        currentUser.setActive(user.isActive());
        userService.update(currentUser);

        UserResponse userResponse = new UserResponse(user);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    /**
     *
     * @param email
     * @return
     */
    @RequestMapping(value = "/rest/user/delete/{email}", method = RequestMethod.DELETE)
    public ResponseEntity<UserResponse> deleteUser(@PathVariable("email") String email) {
        System.out.println("Fetching & Deleting User with email " + email);

        User user = userService.getByEmail(email);
        if (user == null) {
            System.out.println("Unable to delete. User with email " + email + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
