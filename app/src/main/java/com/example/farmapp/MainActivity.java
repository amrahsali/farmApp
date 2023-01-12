package com.example.farmapp;

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
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.product);
    }

    ProductFragment product = new ProductFragment();
    WalletFragment wallet = new WalletFragment();
    profileFragment profile = new profileFragment();


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.product:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, product).commit();
                return true;

            case R.id.wallet:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, wallet).commit();
                return true;

            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, profile).commit();
                return true;
        }

        return false;
    }

}