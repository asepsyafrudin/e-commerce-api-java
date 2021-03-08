package co.g2academy.StoreFront.receiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.g2academy.StoreFront.model.Converter;
import co.g2academy.StoreFront.model.Order;
import co.g2academy.StoreFront.repository.CartRepository;



//Class for listening
@RabbitListener (queues = "OrderFullFillmentMQ")
@Component
public class CartReceiver {
    
    private static final Logger LOG = LoggerFactory.getLogger(CartReceiver.class);
    
    @Autowired
    CartRepository cartRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Converter converter = new Converter();
    
    //receive data form rabbit mq
    @RabbitHandler
    public void receive (byte[] message) {
        String messageBody = new String(message);
        try {
            Order order = objectMapper.readValue(messageBody, Order.class);
            converter.converter(order); // convert order data to be cart and save
        } catch (JsonProcessingException ex) {
            LOG.error(ex.getMessage(), ex);
        } 
    }
}
