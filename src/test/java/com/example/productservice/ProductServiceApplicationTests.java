package com.example.productservice;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ProductServiceApplicationTests {


    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }


    @Test
    public void test(){

        Optional<Category> category = categoryRepository.findByName("electronics");
        List<Product> product = productRepository.findByCategory(category.get());
        System.out.println(product);
    }

    @Test
    public void method_two(){
        List<Product> product = productRepository.findByCategory_Name("electronics");
        System.out.println(product);
    }

    @Test
    public void method_three(){
        List<Product> product = productRepository.getProductBasedOnCategoryNames("electronics");
        System.out.println(product);
    }

    @org.junit.Test
    @Transactional
    public void getCategory(){
        Optional<Category> category = categoryRepository.findById(1L);
        System.out.println(category.get().getProducts());
    }

    @Test
    @Transactional
    public void nplusProblem(){
        List<Category> categories = categoryRepository.findAll();
        for(Category category: categories){
            for(Product product: category.getProducts()){
                System.out.println(product.getTitle());
            }
        }
    }

}
