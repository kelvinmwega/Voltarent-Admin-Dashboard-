package com.vunyx.clientdashboardui.controllers;

import com.google.gson.JsonObject;
import com.vunyx.clientdashboardui.beans.Device;
import com.vunyx.clientdashboardui.beans.DeviceType;
import com.vunyx.clientdashboardui.beans.userRegStatus;
import com.vunyx.clientdashboardui.database.query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("devices")
public class ContentController {

    @Autowired
    RequestsController requestsController;

    @GetMapping("devices")
    public String devices(Model model) {
        userRegStatus stat = new userRegStatus();
        stat.setLoad(false);
        model.addAttribute("stat", stat);
        return "devices";
    }


    @GetMapping("/addDevice")
    public String addDevice(Model model) {
        model.addAttribute("deviceForm", new Device());
        model.addAttribute("devicetypes", requestsController.getDeviceTypes());
        return "/addDevice";
    }

    @PostMapping("/addDevice")
    public String submissionResult(@ModelAttribute("deviceForm") Device device, Model model) {
        userRegStatus stat = new userRegStatus();
        stat.setStatus(requestsController.registerNewDevice(device));
        stat.setLoad(true);
        model.addAttribute("stat", stat);
       return "/devices";
    }

}
