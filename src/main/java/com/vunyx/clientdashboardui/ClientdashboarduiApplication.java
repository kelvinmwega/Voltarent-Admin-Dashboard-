package com.vunyx.clientdashboardui;

import com.vunyx.clientdashboardui.controllers.RequestsController;
import com.vunyx.clientdashboardui.database.query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ClientdashboarduiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientdashboarduiApplication.class, args);

//        RequestsController requestsController = new RequestsController();
//
//        System.out.println(requestsController.regDeviceTypes());
    }

}
