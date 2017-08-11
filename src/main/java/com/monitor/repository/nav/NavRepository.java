package com.monitor.repository.nav;

import com.monitor.model.nav.Nav;

import java.util.List;

/**
 * Created by Azael on 2017/08/10.
 */
public interface NavRepository {
    List<Nav> findAllEnabled();
}
