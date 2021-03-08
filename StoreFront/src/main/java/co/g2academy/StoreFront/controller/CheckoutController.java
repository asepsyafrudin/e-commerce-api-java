package co.g2academy.StoreFront.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.g2academy.StoreFront.model.AddToCart;
import co.g2academy.StoreFront.service.CheckoutService;
import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("api")
public class CheckoutController {

    @Autowired
    CheckoutService checkoutService;

    @PostMapping("/addtocart")
    public String addToCart (@RequestBody AddToCart addToCart, Principal principal) {
            return checkoutService.addToCart(addToCart, getUserName(principal));
        }

    @PostMapping("/checkout/{id}")
    public String checkout (@PathVariable Integer id , Principal principal) {
        return checkoutService.checkout(id, getUserName(principal));
    }

    private String getUserName(Principal principal) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
        Claims user = (Claims) token.getPrincipal();
        return user.getSubject();
    }
}
