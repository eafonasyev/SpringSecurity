package com.eafonasyev.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/showMyLoginForm")
    public String showMyLoginForm(){
        return "fancy-login";
    }
}
