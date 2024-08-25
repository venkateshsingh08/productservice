package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreProductRequestDto;
import com.example.productservice.dtos.FakeStoreProductResponseDto;
import com.example.productservice.models.Product;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreService")
public class FakeStoreProductService implements ProductService{


    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {

        FakeStoreProductResponseDto responseDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductResponseDto.class
        );

        return responseDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
      FakeStoreProductResponseDto []responseDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products" ,
                FakeStoreProductResponseDto[].class
        );

      List<Product> productList = new ArrayList<>();

      for(FakeStoreProductResponseDto dto:responseDto){
          productList.add(dto.toProduct());
      }

        return productList;
    }

    @Override
    public Product createProduct(String title, String description, Double price, String imageUrl, String CategoryName) {

        FakeStoreProductRequestDto requestDto = new FakeStoreProductRequestDto();
        requestDto.setTitle(title);
        requestDto.setDescription(description);
        requestDto.setPrice(price);
        requestDto.setImage(imageUrl);


        FakeStoreProductResponseDto responseDto = restTemplate.postForObject("https://fakestoreapi.com/products",
                requestDto,
                FakeStoreProductResponseDto.class);


        return responseDto.toProduct();


    }

    @Override
    public Product partialUpdateProduct(Long id, Product p) {

        FakeStoreProductRequestDto requestDto = new FakeStoreProductRequestDto();
        requestDto.setTitle(p.getTitle());
        requestDto.setPrice(p.getPrice());
        requestDto.setDescription(p.getDescription());
        //requestDto.setCategory(p.getCategory().getName());
        requestDto.setImage(p.getImageUrl());

        HttpEntity<FakeStoreProductRequestDto> httpEntity = new HttpEntity<>(requestDto);

        ResponseEntity<FakeStoreProductResponseDto> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + id,
                HttpMethod.PATCH,
                httpEntity,
                FakeStoreProductResponseDto.class

        );


        return responseEntity.getBody().toProduct();
    }

    @Override
    public Product deleteProductById(Long Id) {
         ResponseEntity<FakeStoreProductResponseDto> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + Id,
                HttpMethod.DELETE,
                null,
                FakeStoreProductResponseDto.class


        );
         return responseEntity.getBody().toProduct();


    }


}
