package com.example.productservice.dtos;


import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductResponseDto {

    private long id;
    private String title;
    private String price;
    private String category;
    private String description;
    private String image;


    public Product toProduct(){
        Product product = new Product();
        product.setId(this.id);
        product.setTitle(this.title);
        product.setDescription(this.description);
        product.setPrice(Double.parseDouble(this.price));
        product.setImageUrl(this.image);

        Category category1 = new Category();
        category1.setName(this.category);

        product.setCategory(category1);

        return product;
    }


}
