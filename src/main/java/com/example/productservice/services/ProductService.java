package com.example.productservice.services;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import java.util.List;

public interface ProductService {

    public Product getProductById(Long id) throws ProductNotFoundException;

    public List<Product> getAllProducts();

    public Product createProduct(String title, String description,Double price, String imageUrl,String CategoryName);

    public Product partialUpdateProduct(Long id, Product p) throws ProductNotFoundException;

    public Product deleteProductById(Long Id);
}
