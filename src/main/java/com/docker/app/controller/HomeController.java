package com.docker.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(final Model model) {
        model.addAttribute("title", "Docker with Spring Boot and ThemeLeaf !!");
        model.addAttribute("msg", "Welcome to docker containerized application");
        return "index";
    }


}
