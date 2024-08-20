package com.example.productservice.services;

import com.example.productservice.models.Product;
import java.util.List;

public interface ProductService {

    public Product getProductById(Long id);

    public List<Product> getAllProducts();

    public Product createProduct(String title, String description,Double price, String imageUrl,String CategoryName);

    public Product partialUpdateProduct(Long id, Product p);

    public Product deleteProductById(Long Id);
}
