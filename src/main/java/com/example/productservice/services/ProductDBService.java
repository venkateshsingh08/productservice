package com.example.productservice.services;

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
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
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
    public Product partialUpdateProduct(Long id, Product p) {
        return null;
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
            return categoryRepository.save(category);
        }
        return categoryOptional.get();
    }
}
