package com.rathercruel.animelist.security.controllers;

import com.rathercruel.animelist.security.models.ApplicationUser;
import com.rathercruel.animelist.security.services.AuthenticationService;
import com.rathercruel.animelist.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registerPage(Model model) {
        model.addAttribute("userData", "");
        return "register";
    }

    @PostMapping("/registration")
    public ApplicationUser registerUser(@RequestParam(name = "username_input") String username,
                                        @RequestParam(name = "email_input") String email,
                                        @RequestParam(name = "password_input") String password) {
        if (userService.isUserPresent(username)) {
            System.out.println("THE USER HAS NOT BEEN ADDED! [" + username + "]");
            return null; // TODO: HOW DO I HANDLE THIS FREAKING EXCEPTION????
        } else {
            System.out.println("THE USER HAS BEEN ADDED! [" + username + "]");
            return authenticationService.registerUser(username, email, password);
        }
    }
}
