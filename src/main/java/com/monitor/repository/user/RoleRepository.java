package com.monitor.repository.user;

import com.monitor.model.user.Role;

/**
 * Created by Azael on 2017/08/10.
 */
public interface RoleRepository {
    Role findByRole(String role);
}
