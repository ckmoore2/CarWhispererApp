package com.example.carwhispererapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ProductInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(() -> {
            ProductDao productDao = AppDatabase.getInstance(this).productDao();

            //Add product to database
            Product product1 = new Product();
            product1.setName("Product 1");
            product1.setPrice(10.99);
            product1.setBrand("Brand 1");
            product1.setImageUrl("https://example.com/product1.jpg");
            product1.setDetails("Details for Product 1");
            productDao.insertProduct(product1);

            //Add more products to database



            //Fetch and log all products
            List<Product> allProducts = productDao.getAllProducts();
            for (Product product : allProducts) {
                Log.d("Product", "Name: " + product.getName() + ", Price: " + product.getPrice() + ", Brand: " + product.getBrand() + ", Image URL: " + product.getImageUrl() + ", Details: " + product.getDetails());
            }
        }).start();

        }
    }