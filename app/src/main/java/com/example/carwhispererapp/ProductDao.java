package com.example.carwhispererapp;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insertProduct(Product product);

    @Query("SELECT * FROM Product WHERE name LIKE '%' || :search || '%'")
    List<Product> searchProducts(String search);
}
