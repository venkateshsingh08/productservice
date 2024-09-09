package com.example.productservice.services;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service("productDbService")
public class ProductDBService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;


    public ProductDBService(ProductRepository productRepository,CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {

        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return product.get();
        }
        else throw new ProductNotFoundException("Product with id: " +id +" Not Found");
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String title, String description, Double price, String imageUrl, String CategoryName) {

        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);


        product.setCategory(getCategoryForDB(CategoryName));

       return  productRepository.save(product);


    }

    @Override
    public Product partialUpdateProduct(Long id, Product p) throws ProductNotFoundException {

        Optional<Product> productOptional = productRepository.findById(id);

        if(!productOptional.isPresent()){
            throw new ProductNotFoundException("Product does not exist");
        }

        Product productToUpdate = productOptional.get();
        if(p.getTitle()!=null){
            productToUpdate.setTitle(p.getTitle());
        }

        if(p.getDescription()!=null){
            productToUpdate.setTitle(p.getDescription());
        }

        if(p.getImageUrl()!=null){
            productToUpdate.setImageUrl(p.getImageUrl());
        }

        if(p.getPrice()!=null){
            productToUpdate.setPrice(p.getPrice());
        }

        if(p.getCategory()!=null){
            productToUpdate.setCategory(
                    getCategoryForDB(p.getCategory().getName())
            );
        }

        return productRepository.save(productToUpdate);
    }

    @Override
    public Product deleteProductById(Long Id) {
        return null;
    }

    private Category getCategoryForDB(String categoryName){

        Optional<Category> categoryOptional = categoryRepository.findByName(categoryName);

        if(categoryOptional.isEmpty()){
            Category category = new Category();
            category.setName(categoryName);
            return category;
            //return categoryRepository.save(category);
        }
        return categoryOptional.get();
    }
}
