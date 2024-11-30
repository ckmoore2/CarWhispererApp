package com.example.carwhispererapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CartDao {
    @Insert
    void insertCartItem(CartItem cartItem);

    @Query("SELECT * FROM CartItem WHERE userId = :userId")
    List<CartItem> getCartItemsByUserId(int userId);

    @Update
    void updateCartItem(CartItem cartItem);
}
