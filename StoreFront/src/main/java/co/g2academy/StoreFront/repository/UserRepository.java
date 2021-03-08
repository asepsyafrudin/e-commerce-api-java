package co.g2academy.StoreFront.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.g2academy.StoreFront.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByuserName(String userName);
    List<User> findAll();
  
}
