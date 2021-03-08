package co.g2academy.StoreFront.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.g2academy.StoreFront.model.RegisterModel;
import co.g2academy.StoreFront.service.UserService;
import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public String register (@RequestBody RegisterModel register) {
        return userService.save(register);
    }

    @PutMapping("update-user/{id}")
    public String update (@RequestBody RegisterModel register , Principal principal) {
        return userService.update(register, getUserName(principal));
    }

    private String getUserName(Principal principal) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
        Claims user = (Claims) token.getPrincipal();
        return user.getSubject();
   }
}
