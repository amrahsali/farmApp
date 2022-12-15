package com.example.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class User_regActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemReselectedListener {

    BottomNavigationView regNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg);

        regNavigationView = findViewById(R.id.regNavigationView);
        regNavigationView.setOnNavigationItemSelectedListener(this);

        regNavigationView.setSelectedItemId(R.id.user_loginMenu);

    }

    userLoginFragment userlogin = new userLoginFragment();
     UserSignupFragment usersignup  = new UserSignupFragment();
    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.user_loginMenu:
                getSupportFragmentManager().beginTransaction().replace(R.id.userContainer , userlogin).commit();
                return true;

            case R.id.user_signupMenu:
                getSupportFragmentManager().beginTransaction().replace(R.id.userContainer, usersignup).commit();
                return true;


        }

        return false;


    }

}