package com.vunyx.clientdashboardui.controllers;

import com.vunyx.clientdashboardui.beans.Device;
import com.vunyx.clientdashboardui.beans.User;
import com.vunyx.clientdashboardui.beans.userRegStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    RequestsController requestsController;

    @RequestMapping(value = "/index")
    public String main(){
        return "index";
    }

//    @GetMapping("/")
//    public String home1() {
//        return "/home";
//    }

    @GetMapping("/")
    public String dashboard() {
        return "/dashboard";
    }

    @GetMapping("/register")
    public String register(Model model) {
        userRegStatus stat = new userRegStatus();
        stat.setLoad(false);
        model.addAttribute("userForm", new User());
        model.addAttribute("stat", stat);
        return "/register";
    }

    @PostMapping("/register")
    public String submissionResult(@ModelAttribute("userForm") User user, Model model) {
        userRegStatus stat = new userRegStatus();
        stat.setStatus(requestsController.createUser(user));
        stat.setLoad(true);
        model.addAttribute("stat", stat);
        return "/register";
    }

    @GetMapping("/events")
    public String events() {
        return "/events";
    }

    @GetMapping("/emd")
    public String emd(Model model) {
        model.addAttribute("device", new Device());
        model.addAttribute("device", requestsController.getDeviceList("emd"));
        return "/emd";
    }

    @GetMapping("/wlm")
    public String wlm(Model model) {
        model.addAttribute("device", new Device());
        model.addAttribute("device", requestsController.getDeviceList("wlm"));
        return "/wlm";
    }

    @GetMapping("/tracker")
    public String tracker() {
        return "/tracker";
    }

    @GetMapping("/forgot")
    public String forgot() {
        return "/forgot";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }



}
