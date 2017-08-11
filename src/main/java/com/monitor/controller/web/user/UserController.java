package com.monitor.controller.web.user;

import com.monitor.model.user.User;
import com.monitor.service.nav.NavService;
import com.monitor.service.user.LoginService;
import com.monitor.service.user.UserRoleService;
import com.monitor.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by Azael on 2017/08/10.
 */
@Controller
public class UserController {
    private UserService userService;
    private LoginService loginService;
    private NavService navService;
    private UserRoleService userRoleService;

    @Autowired
    public UserController(UserService userService, LoginService loginService, NavService navService, UserRoleService userRoleService) {
        this.userService = userService;
        this.loginService = loginService;
        this.navService = navService;
        this.userRoleService = userRoleService;
    }

    @RequestMapping(value = {"/", "index", "register", "logout"}, method = RequestMethod.GET)
    public ModelAndView index(UserForm userForm) {
        ModelAndView modelAndView = getModelAndView();
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              UserForm userForm) {
        ModelAndView modelAndView = getModelAndView();
        modelAndView.addObject("showLogin", true);
        return modelAndView;
    }

    @RequestMapping(value = "/forgot_password", method = RequestMethod.GET)
    public ModelAndView forgotPassword(UserForm userForm) {
        ModelAndView modelAndView = getModelAndView();
        modelAndView.addObject("showForgotPassword", true);
        return modelAndView;
    }


    @RequestMapping(value = "/forgot_password", method = RequestMethod.POST)
    public ModelAndView changePassword(@Valid UserForm userForm, BindingResult bindingResult) {
        ModelAndView modelAndView = getModelAndView();

        modelAndView.addObject("showForgotPassword", true);
        if (bindingResult.hasErrors()) {
            return modelAndView;
        } else {
            User user = userService.getByEmail(userForm.getEmail());
            if (user == null) {
                bindingResult
                        .rejectValue("email", "error.userForm",
                                "User with the email provided does not exist");
                return modelAndView;
            }
            if (!user.getName().equals(userForm.getName())) {
                bindingResult
                        .rejectValue("name", "error.userForm",
                                "User with the name provided does not exist");
                return modelAndView;
            }
            if (!user.getSurname().equals(userForm.getSurname())) {
                bindingResult
                        .rejectValue("surname", "error.userForm",
                                "User with the surname provided does not exist");
                return modelAndView;
            }
            userRoleService.save(user, "USER");
            user.setPassword(new BCryptPasswordEncoder().encode(userForm.getPassword()));
            userService.update(user);
            loginService.autoLogin(userForm.getEmail());
            modelAndView.addObject("showForgotPassword", null);
            modelAndView.addObject("successMessage", "User password changed successfully");
            return new ModelAndView("redirect:/index");
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView createUser(@Valid UserForm userForm, BindingResult bindingResult) {
        ModelAndView modelAndView = getModelAndView();

        if (userService.isUserExist(userForm.getEmail())) {
            bindingResult
                    .rejectValue("email", "error.userForm",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("showRegister", true);
        } else {
            User user = new User();
            user.setEmail(userForm.getEmail());
            user.setName(userForm.getName());
            user.setSurname(userForm.getSurname());
            user.setPassword(new BCryptPasswordEncoder().encode(userForm.getPassword()));
            user.setActive(true);
            userService.save(user);
            userRoleService.save(user, "USER");
            loginService.autoLogin(userForm.getEmail());
            modelAndView.addObject("successMessage", "User has been registered successfully");
            return new ModelAndView("redirect:/index");
        }
        return modelAndView;
    }

    private ModelAndView getModelAndView() {
        User user = userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        ModelAndView modelAndView = new ModelAndView();
        if (user != null) {
            modelAndView.addObject("user", user);
            modelAndView.setViewName("home/index");
        } else {
            modelAndView.setViewName("index");
        }
        modelAndView.addObject("navs", navService.createNav(user, "Home"));
        return modelAndView;
    }
}
