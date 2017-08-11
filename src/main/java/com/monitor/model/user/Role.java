package com.monitor.model.user;

import com.monitor.model.nav.Nav;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Azael on 2017/08/10.
 */
@Entity
@Table(name = "role", catalog = "monitor")
public class Role {

    @Id
    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "description", nullable = false, unique = true)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<UserRole> userRoles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<Nav> navs = new HashSet<>();

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

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<Nav> getNavs() {
        return navs;
    }

    public void setNavs(Set<Nav> navs) {
        this.navs = navs;
    }
}
