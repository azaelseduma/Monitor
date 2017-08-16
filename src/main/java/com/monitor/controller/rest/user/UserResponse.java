package com.monitor.controller.rest.user;

import com.monitor.model.user.User;
import com.monitor.model.user.UserRole;

import java.util.Date;

/**
 * Created by Azael on 2017/08/14.
 */
public class UserResponse {

    private String email;

    private String password;

    private String name;

    private String surname;

    private boolean active;

    private Date timestamp;

    private RoleResponse[] roleResponses;

    public UserResponse() {
    }

    public UserResponse(User user) {
        email = user.getEmail();
        password = user.getPassword();
        name = user.getName();
        surname = user.getSurname();
        active = user.isActive();
        timestamp = user.getTimestamp();

        roleResponses = new RoleResponse[user.getUserRoles().size()];
        int index = 0;
        for (UserRole userRole : user.getUserRoles()) {
            roleResponses[index] = new RoleResponse(userRole.getRole());
            index++;
        }
    }

    public void print() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Email: " + email);
        System.out.println("Active: " + active);
        System.out.println("Role\tDescription");
        for (RoleResponse roleResponse : roleResponses) {
            System.out.print(roleResponse.getRole() + "\t" + roleResponse.getDescription());
        }
        System.out.println("Time Stamp: " + timestamp);
        System.out.println("------------");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public RoleResponse[] getRoleResponses() {
        return roleResponses;
    }

    public void setRoleResponses(RoleResponse[] roleResponses) {
        this.roleResponses = roleResponses;
    }
}
