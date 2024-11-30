package com.example.carwhispererapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    private int userId;

    private String name;
    private String username;
    private String password;
    private String email;

    // Constructors
    public User() {}

    public User(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters and Setters
    // ... (omitted for brevity)
}
