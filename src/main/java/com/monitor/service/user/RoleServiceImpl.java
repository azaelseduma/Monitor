package com.monitor.service.user;

import com.monitor.model.user.Role;
import com.monitor.repository.user.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Azael on 2017/08/10.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getByRole(String role) {
        return roleRepository.findByRole(role);
    }
}
