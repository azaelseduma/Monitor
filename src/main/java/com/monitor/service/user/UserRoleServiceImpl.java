package com.monitor.service.user;

import com.monitor.model.user.Role;
import com.monitor.model.user.User;
import com.monitor.model.user.UserRole;
import com.monitor.repository.user.RoleRepository;
import com.monitor.repository.user.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Azael on 2017/08/10.
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void save(UserRole userRole) {
        userRoleRepository.save(userRole);
    }

    @Override
    public void delete(UserRole userRole) {
        userRoleRepository.delete(userRole);
    }

    @Override
    public void save(User user, String roleName) {
        Role role = roleRepository.findByRole(roleName);
        if (role != null && !hasRole(user, role)) {
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            userRoleRepository.save(userRole);
        }
    }

    private boolean hasRole(User user, Role role) {
        for (UserRole userRole : user.getUserRoles()) {
            if (userRole.getRole().getRole().equals(role.getRole())) {
                return true;
            }
        }
        return false;
    }
}
