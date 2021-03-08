package co.g2academy.StoreFront.model;

import java.io.Serializable;


public class OrderItems implements Serializable {
 
    private Integer id;

    private Order order;

    private Integer product;

    private String productName;

    private Integer price;
    
    private Integer quantity;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getProduct() {
        return this.product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
