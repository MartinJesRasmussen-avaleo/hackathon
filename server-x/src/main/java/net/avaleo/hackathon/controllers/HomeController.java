package net.avaleo.hackathon.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ras on 25-09-14.
 */

@Controller
public class HomeController {

    public HomeController() {
        System.out.println("HOME CONTROLLER INITIALIZED");
    }

    @RequestMapping(value = {"/", "/index.html", "/index.jsp"})
    public String home() {
        return "index";
    }
}
