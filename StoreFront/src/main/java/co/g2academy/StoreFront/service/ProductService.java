package co.g2academy.StoreFront.service;

import co.g2academy.StoreFront.entity.Product;
import co.g2academy.StoreFront.model.ProductModel;
import co.g2academy.StoreFront.model.ProductUpdateModel;
import co.g2academy.StoreFront.model.UpdateStockProductModel;

public interface ProductService {
    public String save (ProductModel register , String userName);
    public String update (ProductModel product, Integer id, String userName);
    public String update(UpdateStockProductModel product, Integer id, String userName);
    public String delete(Integer id, String userName);
}
