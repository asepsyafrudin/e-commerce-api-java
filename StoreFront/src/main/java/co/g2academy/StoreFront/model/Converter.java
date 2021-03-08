package co.g2academy.StoreFront.model;

import org.springframework.beans.factory.annotation.Autowired;

import co.g2academy.StoreFront.entity.Cart;
import co.g2academy.StoreFront.repository.CartRepository;

public class Converter {

    @Autowired
    CartRepository cartRepository;

    public Cart converter(Order order) {
        Cart cart  = cartRepository.findById(order.getCartId()).get();
        if(cart != null) {
            cart.setStatus(order.getStatus());
            cartRepository.save(cart);
            return cart;
        }
        return null;
    }
}
