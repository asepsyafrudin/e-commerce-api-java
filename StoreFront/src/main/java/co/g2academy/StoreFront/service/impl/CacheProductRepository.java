package co.g2academy.StoreFront.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import co.g2academy.StoreFront.entity.Product;
import co.g2academy.StoreFront.entity.User;
import co.g2academy.StoreFront.repository.ProductRepository;

@Service //cache product to make get product faster
public class CacheProductRepository {

    @Autowired
    private ProductRepository productRepository;

    @Cacheable (value = "findByProductNameContaining")
    public Page<Product> findByProductNameContaining (String productName, 
        Integer page ,
        Integer size,
        String sort) {
            Pageable findByNamePageable = buildPageable(page, size, sort);
            return productRepository.findByProductNameContaining(productName, findByNamePageable);
        }

    private Pageable buildPageable (Integer page,  Integer size, String sort) {
        Sort.Order order = null;
        if("cheapest".equals(sort)) {
            order = new Sort.Order(Sort.Direction.ASC , "price");
        } else if ("expensive".equals(sort)) {
            order = new Sort.Order(Sort.Direction.DESC, "price");
        } else if ("alphabetical".equals(sort)) {
            order = new Sort.Order(Sort.Direction.ASC, "productName");
        }
        return PageRequest.of(page, size, Sort.by(order));
    }

    @Cacheable(value= "findByCategory")
    public Page<Product> findByCategory(String category, 
        Integer page ,
        Integer size,
        String sort) {
            Pageable findByCategory = buildPageable(page, size, sort);
            return productRepository.findByCategoryContaining(category, findByCategory);
    }

    @Cacheable(value = "findById")
    public Product findById (Integer id) {
        Optional<Product> optional = productRepository.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Cacheable(value = "findAll")
    public Page<Product> findAll(Integer page ,Integer size, String sort) { 
        return productRepository.findAll(buildPageable(page, size, sort));
    }

    @Cacheable(value = "findByUser")
    public Page<Product> findByUser (User user , Integer page, Integer size, String sort) {
        return productRepository.findByUser(user, buildPageable(page, size, sort));
    }
}
