package co.g2academy.StoreFront.service;

import co.g2academy.StoreFront.model.AddToCart;

public interface CheckoutService {
    public String addToCart(AddToCart addtocart , String userName);
    public String checkout(Integer id ,String userName);
    
}
