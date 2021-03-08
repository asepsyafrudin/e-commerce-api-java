package co.g2academy.orderfullfillment.model;

import java.io.Serializable;

public class CartItems implements Serializable {
   
    private Integer id;

    private Cart cart;

    private Product Product;

    private Integer quantity;

    private Integer price;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cart getCart() {
        return this.cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return this.Product;
    }

    public void setProduct(Product Product) {
        this.Product = Product;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


    
}
