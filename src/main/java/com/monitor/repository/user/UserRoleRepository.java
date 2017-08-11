package com.monitor.repository.user;

import com.monitor.model.user.UserRole;

/**
 * Created by Azael on 2017/08/10.
 */
public interface UserRoleRepository {
    void save(UserRole userRole);

    void delete(UserRole userRole);
}
