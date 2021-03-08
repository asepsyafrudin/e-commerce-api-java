package co.g2academy.StoreFront.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.g2academy.StoreFront.entity.Product;
import co.g2academy.StoreFront.entity.User;
import co.g2academy.StoreFront.model.ProductModel;
import co.g2academy.StoreFront.model.UpdateStockProductModel;
import co.g2academy.StoreFront.repository.UserRepository;
import co.g2academy.StoreFront.service.ProductService;
import co.g2academy.StoreFront.service.impl.CacheProductRepository;
import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private CacheProductRepository cacheProductRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/search")
    public ResponseEntity <Map<String , Object>> getProductBySearchQuery(
        @RequestParam String productName,
        @RequestParam Integer page, 
        @RequestParam Integer size, 
        @RequestParam String sort) {
            return buildResponseEntity(cacheProductRepository.findByProductNameContaining(productName, page, size, sort));
    }

    private ResponseEntity<Map<String, Object>> buildResponseEntity(Page<Product> productPage) {
        Map<String, Object> response = new HashMap<>();
        response.put("products", productPage.getContent());
        response.put("currentPage", productPage.getNumber());
        response.put("totalItem", productPage.getTotalElements());
        response.put("totalPages", productPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/category") // Get product by category api
    private ResponseEntity<Map<String, Object>> getProductByCategoryQuery (
        @RequestParam String category,
        @RequestParam Integer page,
        @RequestParam Integer size,
        @RequestParam String sort) {
            return buildResponseEntity(cacheProductRepository.findByCategory(category, page, size, sort));
    }

    @GetMapping("/own-products") // Get own product for user Login api
    private ResponseEntity<Map<String, Object>> getOwnProduct (
        @RequestParam Integer page,
        @RequestParam Integer size,
        @RequestParam String sort,
        Principal principal) {
            User user = userRepository.findByuserName(getUserName(principal));
            return buildResponseEntity(cacheProductRepository
                   .findByUser(user, page, size, sort));
        }

    @PostMapping("/save-product") // save product api
    public String saveProduct (
        @RequestBody ProductModel productModel,
        Principal principal){
            return productService.save(productModel, getUserName(principal));    
    }

    private String getUserName(Principal principal) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
        Claims user = (Claims) token.getPrincipal();
        return user.getSubject();
   }

   //update pproduct by id (it can do if user of product registered same with login user)
   @PutMapping("/update-product/{id}") 
   public String updateProduct(
       @RequestBody ProductModel product,  @PathVariable Integer id,
       Principal principal) {
           return productService.update(product , id , getUserName(principal));
    }  

    //update stock product by id (it can do if user of product registered same with login user)
    @PutMapping("update-product/stock/{id}")
    public String updateStockProduct(
        @RequestBody UpdateStockProductModel product,  @PathVariable Integer id,
        Principal principal) {
            return productService.update(product, id, getUserName(principal));
        }

    //delet product by id api
    @DeleteMapping ("delete-product/{id}")
    public String deleteProduct(@PathVariable Integer id , Principal principal){
            return productService.delete(id, getUserName(principal));
        }
}
