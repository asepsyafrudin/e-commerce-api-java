package co.g2academy.StoreFront.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.g2academy.StoreFront.entity.Cart;
import co.g2academy.StoreFront.entity.User;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
    Cart findByUserAndStatus(User user, String status);

}
