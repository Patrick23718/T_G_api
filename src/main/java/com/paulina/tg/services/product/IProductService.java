package com.paulina.tg.services.product;

import com.paulina.tg.models.Category;
import com.paulina.tg.models.Product;
import com.paulina.tg.request.ProductRequest;
import org.hibernate.query.Page;

import java.util.List;

public interface IProductService {
    Product addProduct(ProductRequest product);
    Product updateProduct(ProductRequest product, String id);
    Product getProductById(String id);
    List<Product> getAllProductsByActiveAndNameAndBrand(boolean active, String name, String brand);
//    Product updateProductInventory(String productId, int inventory);
    Product updateProductStock(String productId, int stock);
    Product updateProductStatus(String productId);
    List<Product> getAllProductsByCategory(String categoryId);
}
