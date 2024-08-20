package com.example.productservice.dtos;


import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {


    private long id;
    private String title;

    private String description;

    private double price;

    private String imageUrl;

    private String categoryName;

    public ProductResponseDto fromProduct(Product product){

        ProductResponseDto  responseDto = new ProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setTitle(product.getTitle());
        responseDto.setPrice(product.getPrice());
        responseDto.setImageUrl(product.getImageUrl());
        responseDto.setCategoryName(product.getCategory().getName());
        responseDto.setDescription(product.getDescription());

        return responseDto;
    }
}
