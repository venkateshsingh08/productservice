package com.example.productservice.repositories;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    //used for both saving and updating
    //if id is present it will update
    //if id is not present, it will insert.
    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findById(Long Id);

    List<Product> findByCategory(Category category);

    List<Product> findByCategory_Name(String categoryName);

    @Query("select p from Product p where p.category.name = :categoryName")
    List<Product> getProductBasedOnCategoryNames(@Param("categoryName") String categoryName);


    @Query(value = "select * from Product p join Category  c where p.category_id =c.id",nativeQuery = true)
    List<Product> getProductBasedOnCategoryNames2();
}
