package com.example.productservice.controllers;


import com.example.productservice.commons.AuthenticationCommons;
import com.example.productservice.dtos.ProductRequestDto;
import com.example.productservice.dtos.ProductResponseDto;
import com.example.productservice.dtos.UserDto;
import com.example.productservice.exceptions.InvalidTokenException;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private ProductService productService ;
    private AuthenticationCommons authenticationCommons;

    @Autowired
    public ProductController(@Qualifier("productDbService") ProductService productService,AuthenticationCommons authenticationCommons) {

        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
    }

    @GetMapping("/product/{id}")
    public ProductResponseDto getProductById(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) throws InvalidTokenException {

        UserDto userDto = authenticationCommons.validateToken(token);
        if(userDto==null){
            throw new InvalidTokenException("Invalid Token");
        }

        try {
            ProductResponseDto productResponseDto = new ProductResponseDto();
            Product product = productService.getProductById(id);
            return productResponseDto.fromProduct(product);

        }
        catch(ProductNotFoundException productNotFoundException){
            return null;
        }




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
    public List<ProductResponseDto> getAllProducts(){

        List<Product> productList = productService.getAllProducts();

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        for(Product p: productList){
            ProductResponseDto dto = new ProductResponseDto();
            productResponseDtos.add(dto.fromProduct(p));
        }
        return productResponseDtos;
    }

    @PostMapping("/product")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto){

        Product product = productService.createProduct(productRequestDto.getTitle(), productRequestDto.getDescription(),
                productRequestDto.getPrice(), productRequestDto.getImageUrl(), productRequestDto.getCategoryName());

        ProductResponseDto responseDto = new ProductResponseDto();
        return  responseDto.fromProduct(product);

    }
    @DeleteMapping("/product/{id}")
    public ProductResponseDto deleteProduct(@PathVariable("id")Long id){

         Product product = productService.deleteProductById(id);
         ProductResponseDto productResponseDto = new ProductResponseDto();
         return productResponseDto.fromProduct(product);


    }

    @PatchMapping("/product/{id}")
    public Product partialUpdateProduct(@PathVariable("id")Long id,@RequestBody ProductRequestDto productRequestDto) throws ProductNotFoundException {

        Product p = new Product();
        p.setId(id);
        p.setPrice(productRequestDto.getPrice());
        p.setTitle(productRequestDto.getTitle());
        p.setDescription(productRequestDto.getDescription());

        return  productService.partialUpdateProduct(id,p);

    }
}
