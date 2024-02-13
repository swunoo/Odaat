package dev.odaat.Controller.Api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.odaat.Entity.Enums.UserAuth;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String showLoginPage(){
        return "login";
    }

    @PostMapping
    public String attemptLogin(@ModelAttribute UserAuth userAuth){
        if(userAuth.getUsername() == "admin" && userAuth.getPassword() == "password"){
            return "/";
        } else return "/login";
    }
}
