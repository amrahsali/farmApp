package com.example.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.userBottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        bottomNavigationView.setSelectedItemId(R.id.user_product);
    }

    User_productFragment userProduct = new User_productFragment();
    User_cartFragment userCart  = new User_cartFragment();
    User_ProfileFragment userprofile = new User_ProfileFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {



        switch (item.getItemId()) {
            case R.id.user_product:
                getSupportFragmentManager().beginTransaction().replace(R.id.userContainer , userProduct).commit();
                return true;

            case R.id.user_cart:
                getSupportFragmentManager().beginTransaction().replace(R.id.userContainer, userCart).commit();
                return true;

            case R.id.user_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.userContainer, userprofile).commit();
                return true;


        }

        return false;
    }
}