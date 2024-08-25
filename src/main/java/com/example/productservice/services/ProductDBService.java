package com.example.productservice.services;

import com.example.productservice.models.Product;
import java.util.List;
import org.springframework.stereotype.Service;

@Service("productDbService")
public class ProductDBService implements ProductService{
    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product createProduct(String title, String description, Double price, String imageUrl, String CategoryName) {
        return null;
    }

    @Override
    public Product partialUpdateProduct(Long id, Product p) {
        return null;
    }

    @Override
    public Product deleteProductById(Long Id) {
        return null;
    }
}
