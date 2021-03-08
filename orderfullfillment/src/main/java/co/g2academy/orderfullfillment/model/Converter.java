package co.g2academy.orderfullfillment.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.g2academy.orderfullfillment.entity.Order;
import co.g2academy.orderfullfillment.entity.OrderItems;

public class Converter  {
    public Order convert (Cart cart) {
        Order order = new Order();
        order.setCartId(cart.getId());
        order.setStatus("PROCESSED");
        order.setTransactionDate(new Date());
        order.setUser(cart.getUser().getId());
        List<OrderItems> orderItem = new ArrayList<>();
        order.setOrderItems(orderItem);

        Integer totalPrice = 0;
        for (CartItems cartItems : cart.getCartsItems()) {
            OrderItems orderItems = new OrderItems();
            orderItems.setOrder(order);
            orderItems.setPrice(cartItems.getPrice());
            orderItems.setProduct(cartItems.getProduct().getId());
            orderItems.setProductName(cartItems.getProduct().getProductName());
            orderItems.setQuantity(cartItems.getQuantity());
            totalPrice += cartItems.getPrice()*cartItems.getQuantity();
            orderItem.add(orderItems);
        }
        order.setTotalPrice(totalPrice);
        return order;
    }
}
