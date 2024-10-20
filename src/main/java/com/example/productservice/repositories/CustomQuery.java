package com.example.productservice.repositories;

public class CustomQuery {

    public static final String GET_PRODUCTS_FROM_CATEGORY_NAME = "select * from Product p join Category  c where p.category_id =c.id";
}
