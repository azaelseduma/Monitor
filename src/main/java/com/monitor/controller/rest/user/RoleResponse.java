package com.monitor.controller.rest.user;

import com.monitor.model.user.Role;

/**
 * Created by Azael on 2017/08/14.
 */
public class RoleResponse {
    private String role;
    private String description;

    public RoleResponse() {
    }

    public RoleResponse(Role role) {
        this.role = role.getRole();
        this.description = role.getDescription();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
