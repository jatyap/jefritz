package com.jatyap.jefritz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController {

    @RequestMapping("/")
    public String mainPage(Model model) {
        return "index";
    }
    
    @RequestMapping("/login")
    public String login(Model model){
    	return "login";
    }
}
