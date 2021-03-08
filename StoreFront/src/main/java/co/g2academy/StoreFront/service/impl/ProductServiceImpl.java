package co.g2academy.StoreFront.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.g2academy.StoreFront.entity.Product;
import co.g2academy.StoreFront.entity.User;
import co.g2academy.StoreFront.model.ProductModel;
import co.g2academy.StoreFront.model.ProductUpdateModel;
import co.g2academy.StoreFront.model.UpdateStockProductModel;
import co.g2academy.StoreFront.repository.ProductRepository;
import co.g2academy.StoreFront.repository.UserRepository;
import co.g2academy.StoreFront.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public String save(ProductModel register, String userName) {
        Product newProduct = new Product();
        User user = userRepository.findByuserName(userName);
        if (user != null) {
            newProduct.setProductName(register.getProductName());
            newProduct.setDescription(register.getDescription());
            newProduct.setCategory(register.getCategory());
            newProduct.setPrice(register.getPrice());
            newProduct.setStock(register.getStock());
            newProduct.setUser(user);
            productRepository.save(newProduct);
            return "Product Already Save";
        }
        return "fail";
    }

    @Override
    public String update(ProductModel product, Integer id, String userName) {
        Product p = productRepository.findById(id).get();
        User user = userRepository.findByuserName(userName);
        if (p != null) {
            if (p.getUser().equals(user)) {
                p.setCategory(product.getCategory());
                p.setDescription(product.getDescription());
                p.setProductName(product.getProductName());
                p.setPrice(product.getPrice());
                p.setStock(product.getStock());
                p.setUser(user);
                productRepository.save(p);
                return "Product has been updated";
            } else {
                return "You don't have authorized to change for this product";
            }
        }
        return null;
    }

    @Override
    public String update(UpdateStockProductModel product, Integer id, String userName) {
        Product p = productRepository.findById(id).get();
        User user = userRepository.findByuserName(userName);
        if(p != null) {
            if(p.getUser().equals(user)) {
                p.setStock(product.getStock());
                productRepository.save(p);
                return "Product has been updated";
            } else {
                return "You don't have authorized to change for this product";
            } 
        } else {
            return " Product Null";
        }
    }

    @Override
    public String delete(Integer id, String userName) {
        Product product = productRepository.findById(id).get();
        User user = userRepository.findByuserName(userName);
        if(product != null) {
            if(product.getUser().equals(user)) {
                productRepository.deleteById(id);
                return "Product has been delete";
                } else {
                    return "You don't have authorized to change for this product";
                }
        } else {
            return "Product null";
        }
    }    
}
