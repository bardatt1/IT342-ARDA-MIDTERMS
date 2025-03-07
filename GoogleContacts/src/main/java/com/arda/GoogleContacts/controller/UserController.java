package com.arda.GoogleContacts.controller;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    // Handle GET requests to the "/user-info" endpoint
    @GetMapping("/user-info")
    public Map<String, Object> getUser(@AuthenticationPrincipal OAuth2User oAuth2User) {
        // Return the user's attributes as a map
        return oAuth2User.getAttributes();
    }

    // Handle GET requests to the "/user" endpoint
    @GetMapping("/user")
    public OAuth2User get(@AuthenticationPrincipal OAuth2User oAuth2User) {
        // Return the entire OAuth2User object
        return oAuth2User;
    }
}
