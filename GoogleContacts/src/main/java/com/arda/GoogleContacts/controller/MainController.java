package com.arda.GoogleContacts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // Handle GET requests to the root ("/") endpoint
    @GetMapping("/")
    public String home() {
        // Redirect to the login page
        return "redirect:/login";
    }

    // Handle GET requests to the "/login" endpoint
    @GetMapping("/login")
    public String login() {
        // Return the name of the login view
        return "login";
    }

    // Handle GET requests to the "/index" endpoint
    @GetMapping("/index")
    public String index() {
        // Return the name of the index view
        return "index";
    }
}