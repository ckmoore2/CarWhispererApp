package com.example.carwhispererapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insertProducts(List<Product> products);

    @Query("SELECT * FROM Product")
    List<Product> getAllProducts();

    @Query("SELECT * FROM Product WHERE name LIKE '%' || :search || '%'")
    List<Product> searchProducts(String search);

    @Update
    void updateProduct(Product product);
}
