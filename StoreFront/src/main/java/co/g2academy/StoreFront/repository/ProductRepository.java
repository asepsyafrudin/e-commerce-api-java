package co.g2academy.StoreFront.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.g2academy.StoreFront.entity.Product;
import co.g2academy.StoreFront.entity.User;

@Repository
public interface ProductRepository extends CrudRepository<Product , Integer> {
    Page<Product> findByProductNameContaining(String productName, Pageable pageable);
    Page<Product> findByCategoryContaining(String category, Pageable pageable);
    Page<Product> findAll(Pageable pageable);
    Page<Product> findByUser(User user , Pageable pageable);
 
}
