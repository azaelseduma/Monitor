package com.monitor.service.nav;

import com.monitor.model.nav.Nav;
import com.monitor.model.user.User;

import java.util.List;

/**
 * Created by Azael on 2017/08/10.
 */
public interface NavService {
    List<Nav> getAllEnabled();

    List<Nav> createNav(User user, String activeNav);
}
