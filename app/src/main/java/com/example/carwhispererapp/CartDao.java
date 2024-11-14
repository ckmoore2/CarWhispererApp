package com.example.carwhispererapp;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface CartDao {
    @Insert
    void addToCart(CartItem cartItem);

    @Query("SELECT * FROM CartItem WHERE userId = :userId")
    List<CartItem> getUserCart(int userId);

    @Query("DELETE FROM CartItem WHERE cartItemId = :cartItemId")
    void removeFromCart(int cartItemId);
}
