package com.eafonasyev.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHomePage(){
        return "home";
    }
    @GetMapping("/leader")
    public String showLeaderPage(){
        return "leader";
    }

    @GetMapping("/admin")
    public String showAdminPage(){
        return "admin";
    }
}

