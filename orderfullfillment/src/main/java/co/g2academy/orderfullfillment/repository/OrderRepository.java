package co.g2academy.orderfullfillment.repository;

import org.springframework.data.repository.CrudRepository;

import co.g2academy.orderfullfillment.entity.Order;

public interface OrderRepository extends CrudRepository<Order , Integer>{
    
}
