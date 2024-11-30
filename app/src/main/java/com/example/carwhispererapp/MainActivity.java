package com.example.carwhispererapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProductViewModel productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        productViewModel.getAllProducts().observe(this, products -> {
            // TODO: Update RecyclerView or UI with products
            for (Product product : products) {


            }


        });
    }
}
