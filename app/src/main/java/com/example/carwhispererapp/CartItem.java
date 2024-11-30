package com.example.carwhispererapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CartItem {
    @PrimaryKey(autoGenerate = true)
    private int cartItemId;
    private int userId;
    private int productId;
    private int quantity;

    // Constructors
    public CartItem() {}

    public CartItem(int userId, int productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters and Setters
    // ... (omitted for brevity)
}
