package com.monitor.service.user;

import com.monitor.model.user.User;
import com.monitor.model.user.UserRole;

/**
 * Created by Azael on 2017/08/10.
 */
public interface UserRoleService {
    void save(UserRole userRole);

    void delete(UserRole userRole);

    void save(User user, String roleName);
}
