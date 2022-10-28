package com.example.farmapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LoginActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView loginNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginNavigationView = findViewById(R.id.loginNavigationView);
        loginNavigationView.setOnNavigationItemSelectedListener(this);

        loginNavigationView.setSelectedItemId(R.id.farmers);

    }

    FarmersLoginFragment farmers = new FarmersLoginFragment();
    UsersFragment users = new UsersFragment();



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.farmers:
                getSupportFragmentManager().beginTransaction().replace(R.id.container2, farmers).commit();
                return true;

            case R.id.users:
                getSupportFragmentManager().beginTransaction().replace(R.id.container2, users).commit();
                return true;

        }

        return false;
    }


    public void TosignUp(View view) {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }
}