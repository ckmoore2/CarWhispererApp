package com.example.carwhispererapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class ProductInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(() -> {
            ProductDao productDao = AppDatabase.getInstance(context).productDao();
            List<Product> products = Arrays.asList(
                    new Product("Battery", 120.99, "Duracell", "...", "..."),
                    new Product("Brake Pads", 50.49, "Brembo", "...", "...")
            );
            productDao.insertProducts(products);
        }).start();

        //Add more products to database



            //Fetch and log all products from database
        new Thread(() -> {
            ProductDao productDao = AppDatabase.getInstance(context).productDao();
            List<Product> allProducts = productDao.getAllProducts();
            for (Product product : allProducts) {
                Log.d("ProductInfoActivity", "Product: " + product.getName());

        }
    }