package co.g2academy.orderfullfillment.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.g2academy.orderfullfillment.service.impl.OrderServiceImpl;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderServiceImpl orderServiceImpl;
    
    @PutMapping("/shipped")
    public String shipped(@RequestBody Integer id) {
        return orderServiceImpl.shipped(id);

    }
}
