package com.monitor.controller.web.about;

import com.monitor.controller.web.user.UserForm;
import com.monitor.model.about.ContactUs;
import com.monitor.model.user.User;
import com.monitor.service.about.ContactUsService;
import com.monitor.service.nav.NavService;
import com.monitor.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by Azael on 2017/08/10.
 */
@Controller
public class AboutController {
    private UserService userService;
    private NavService navService;
    private ContactUsService contactUsService;

    @Autowired
    public AboutController(UserService userService, NavService navService, ContactUsService contactUsService) {
        this.userService = userService;
        this.navService = navService;
        this.contactUsService = contactUsService;
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public ModelAndView about(UserForm userForm, ContactUsForm contactUsForm) {
        ModelAndView modelAndView = getModelAndView();
        return modelAndView;
    }

    @RequestMapping(value = "/contact_us", method = RequestMethod.POST)
    public ModelAndView contactUs(UserForm userForm, @Valid ContactUsForm contactUsForm, BindingResult bindingResult) {
        ModelAndView modelAndView = getModelAndView();
        if (bindingResult.hasErrors()) {
            return modelAndView;
        } else {
            ContactUs contactUs = new ContactUs();
            contactUs.setName(contactUsForm.getName());
            contactUs.setSurname(contactUsForm.getSurname());
            contactUs.setEmail(contactUsForm.getEmail());
            contactUs.setPhoneNumber(contactUsForm.getPhoneNumber());
            contactUs.setMessage(contactUsForm.getMessage());
            contactUsService.save(contactUs);
        }
        return modelAndView;
    }

    private ModelAndView getModelAndView() {
        User user = userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("about/index");
        modelAndView.addObject("navs", navService.createNav(user, "About"));
        return modelAndView;
    }
}
