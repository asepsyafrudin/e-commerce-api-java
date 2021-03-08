package co.g2academy.StoreFront.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.g2academy.StoreFront.appconfig.AppConfig;
import co.g2academy.StoreFront.entity.Cart;
import co.g2academy.StoreFront.entity.CartItems;
import co.g2academy.StoreFront.entity.Product;
import co.g2academy.StoreFront.entity.User;
import co.g2academy.StoreFront.model.AddToCart;
import co.g2academy.StoreFront.repository.CartRepository;
import co.g2academy.StoreFront.repository.ProductRepository;
import co.g2academy.StoreFront.repository.UserRepository;
import co.g2academy.StoreFront.service.CheckoutService;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired RabbitTemplate rabbitTemplate;

    private static final Logger LOG = LoggerFactory.getLogger(CheckoutServiceImpl.class);

    @Override
    public String addToCart(AddToCart addtocart, String userName) {
        User user = userRepository.findByuserName(userName);
        Cart cart = cartRepository.findByUserAndStatus(user, "ACTIVE");
        if(cart == null) {
            cart = new Cart();
            cart.setStatus("ACTIVE");
            cart.setTransactionDate(new Date());
            cart.setUser(user);
            List<CartItems> items = new ArrayList<>();
            cart.setCartsItems(items);
        }

        Product product = productRepository.findById(addtocart.getProductId()).get();
        if(product != null && product.getStock() != 0) {
            CartItems cartItems = new CartItems();
            cartItems.setCart(cart);
            cartItems.setPrice(product.getPrice());
            cartItems.setProduct(product);
            cartItems.setQuantity(addtocart.getQuantity());
            cart.getCartsItems().add(cartItems);

            //decrease stock product
            int stockProduct = product.getStock();
            int stockBuyByCust = addtocart.getQuantity();
            int currentStock = stockProduct - stockBuyByCust;
                if (currentStock >= 0) {
                    cartRepository.save(cart);
                    product.setStock(currentStock);
                    productRepository.save(product);
                } else {
                    return "There is only stock of " + stockProduct ;  
                }
            return "Add to Cart Was Successfull";
        }
        return "Fail add to cart";  
    }

    @Override
    public String checkout(Integer id , String userName) {
        Cart cart = cartRepository.findById(id).get();
        if (cart.getUser().getUserName().equals(userName) && cart.getStatus().equals("ACTIVE")) {
            cart.setStatus("ONPROGRESS");
            LOG.info("sending message to AMQP");
            rabbitTemplate.convertAndSend(AppConfig.QUEUE_NAME, cart);
            cartRepository.save(cart);
            return "Product will be processed";
        } else {
            return "Product is on going processed";
        }
    }    
}
