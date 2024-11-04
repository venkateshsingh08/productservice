package com.example.productservice.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel implements Serializable {

    private String title;

    private String description;

    private Double price;

    private String imageUrl;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;

    //private String abs; -> Gives error because not present in db.
}
