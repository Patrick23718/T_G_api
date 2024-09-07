package com.paulina.tg.services.product;

import com.paulina.tg.exceptions.ResourceNotFoundException;
import com.paulina.tg.models.Product;
import com.paulina.tg.repositories.ProductRepository;
import com.paulina.tg.request.ProductRequest;
import com.paulina.tg.services.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    @Override
    public Product addProduct(ProductRequest product) {
        Product newProduct = Product.builder()
                .inventory(product.getInventory())
                .name(product.getName())
                .price(product.getPrice())
                .active(false)
                .brand(product.getBrand())
                .category(categoryService.findCategoryById(product.getCategoryId()))
                .secure_inventory(product.getSecure_inventory())
                .description(product.getDescription())
                .build();
        return productRepository.save(newProduct);
    }

    @Override
    public Product updateProduct(ProductRequest product, String id) {
        return Optional.ofNullable(getProductById(id))
                .map(oldProduct -> {
                    oldProduct.setName(product.getName());
                    oldProduct.setPrice(product.getPrice());
                    oldProduct.setCategory(categoryService.findCategoryById(product.getCategoryId()));
                    oldProduct.setSecure_inventory(product.getSecure_inventory());
                    oldProduct.setDescription(product.getDescription());
                    oldProduct.setBrand(product.getBrand());
                    return productRepository.save(oldProduct);
                })
                .orElseThrow(()-> new ResourceNotFoundException("Product not found!"));
    }

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public List<Product> getAllProductsByActiveAndNameAndBrand(boolean active, String name, String brand) {
        return productRepository.findByActiveIsAndNameContainingIgnoreCaseAndBrandContainingIgnoreCase(active, name, brand);
    }

    @Override
    public Product updateProductStock(String productId, int stock) {
        return Optional.ofNullable(getProductById(productId))
                .map(oldProduct -> {
                    oldProduct.setInventory(oldProduct.getInventory() + stock);
                    return productRepository.save(oldProduct);
                })
                .orElseThrow(()-> new ResourceNotFoundException("Product not found!"));
    }

    @Override
    public Product updateProductStatus(String productId) {
        return Optional.ofNullable(getProductById(productId))
                .map(oldProduct -> {
                    oldProduct.setActive(!oldProduct.isActive());
                    return productRepository.save(oldProduct);
                })
                .orElseThrow(()-> new ResourceNotFoundException("Product not found!"));
    }

    @Override
    public List<Product> getAllProductsByCategory(String id) {
        return productRepository.findByCategory(categoryService.findCategoryById(id));
    }
}
