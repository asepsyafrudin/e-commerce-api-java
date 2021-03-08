package co.g2academy.orderfullfillment.receiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.g2academy.orderfullfillment.entity.Order;
import co.g2academy.orderfullfillment.model.Cart;
import co.g2academy.orderfullfillment.model.Converter;
import co.g2academy.orderfullfillment.repository.OrderRepository;

//Class for listening
@RabbitListener (queues = "frontStoreq")
@Component
public class CheckoutReceiver {
    
    private static final Logger LOG = LoggerFactory.getLogger(CheckoutReceiver.class);
    
    @Autowired
    OrderRepository orderRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Converter converter = new Converter();
    
    @RabbitHandler
    public void receive (byte[] message) {
        String messageBody = new String(message);
        try {
            Cart cart = objectMapper.readValue(messageBody, Cart.class);
            Order order =  converter.convert(cart);
            orderRepository.save(order);
        } catch (JsonProcessingException ex) {
            LOG.error(ex.getMessage(), ex);
        } 
    }
}
