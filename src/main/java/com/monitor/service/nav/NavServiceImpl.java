package com.monitor.service.nav;

import com.monitor.model.nav.Nav;
import com.monitor.model.user.User;
import com.monitor.model.user.UserRole;
import com.monitor.repository.nav.NavRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Azael on 2017/08/10.
 */
@Service("navService")
public class NavServiceImpl implements NavService {

    @Autowired
    private NavRepository navRepository;

    @Override
    public List<Nav> getAllEnabled() {
        return navRepository.findAllEnabled();
    }

    @Override
    public List<Nav> createNav(User user, String activeNav) {
        List<Nav> navs = new ArrayList<>();

        List<Nav> navList = navRepository.findAllEnabled();
        navs.addAll(navList.stream().filter(nav -> nav.getRole().getDescription().equals("ANONYMOUS")).collect(Collectors.toList()));

        if (user != null) {
            for (Nav nav : navList) {
                for (UserRole userRole : user.getUserRoles()) {
                    if (userRole.getRole().getRole().equals(nav.getRole().getRole())) {
                        navs.add(nav);
                        break;
                    }
                }
            }
        }

        for (Nav nav : navs) {
            if (nav.getName().equals(activeNav)) {
                nav.setCssClass("active");
            } else if (nav.getCssClass().equals("active")) {
                nav.setCssClass(nav.getHref().equals("#") ? "parent" : "");
            }
        }

        return navs;
    }
}
