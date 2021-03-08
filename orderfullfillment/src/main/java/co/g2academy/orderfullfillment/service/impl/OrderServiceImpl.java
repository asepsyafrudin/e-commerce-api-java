package co.g2academy.orderfullfillment.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.g2academy.orderfullfillment.appconfig.AppConfig;
import co.g2academy.orderfullfillment.entity.Order;
import co.g2academy.orderfullfillment.repository.OrderRepository;
import co.g2academy.orderfullfillment.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired 
    RabbitTemplate rabbitTemplate;

    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public String shipped(Integer id) {
        Order order = orderRepository.findById(id).get();
        if(order!= null) {
            order.setStatus("DELIVERED");
            orderRepository.save(order);
            //send back to rabbit MQ 
            LOG.info("sending message to AMQP");
            rabbitTemplate.convertAndSend(AppConfig.QUEUE_NAME, order);
            return "Product on delivering";
        }
        return null;
    }
}
