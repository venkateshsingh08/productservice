package com.example.productservice.controllers;


import com.example.productservice.dtos.ProductResponseDto;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private ProductService productService ;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public ProductResponseDto getProductById(@PathVariable("id")Long id){

        Product product = productService.getProductById(id);
        ProductResponseDto productResponseDto = new ProductResponseDto();
        return productResponseDto.fromProduct(product);

//        ProductResponseDto dummy = new ProductResponseDto();
//        dummy.setId(1L);
//        dummy.setTitle("abc");
//        dummy.setPrice(20);
//        dummy.setDescription("new product");
//        dummy.setImageUrl("none");
//        ResponseEntity <ProductResponseDto> responseEntity = new ResponseEntity<>(dummy, HttpStatusCode.valueOf(202));
//        return responseEntity;

    }


    @GetMapping("/product")
    public String getAllProducts(){
        return "Hello world !";
    }

    public void createProduct(){}

    public void deleteProduct(){}

    public void updateProduct(){}
}
