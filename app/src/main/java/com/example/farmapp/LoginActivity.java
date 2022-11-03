package com.example.farmapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LoginActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    CheckBox checkBox;
    Context context;
    Resources resources;
    BottomNavigationView loginNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


            checkBox = findViewById(R.id.checkboxId);

//            if (checkBox.isChecked()){
//                context = LocaleHelper.setLocale(this, "hausa");
//                resources = context.getResources();
//                Toast.makeText(this, "checkbox is checked", Toast.LENGTH_SHORT).show();
//            }else {
//                Toast.makeText(this, "checkbox is unchecked", Toast.LENGTH_SHORT).show();
//                context = LocaleHelper.setLocale(this, "English");
//                resources = context.getResources();
//            }
            checkBox.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (checkBox.isChecked()) {
                                context = LocaleHelper.setLocale(LoginActivity.this, "ha");
                                resources = context.getResources();
                                Intent refresh = new Intent(context, LoginActivity.class);
                                finish();
                                startActivity(refresh);

                                //Toast.makeText(this, "checkbox is checked", Toast.LENGTH_SHORT).show();
                                Toast.makeText(LoginActivity.this, "checkbox is checked", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText( LoginActivity.this, "checkbox is unchecked", Toast.LENGTH_SHORT).show();
                                context = LocaleHelper.setLocale(LoginActivity.this, "en");
                                resources = context.getResources();
                                Intent refresh = new Intent(context, LoginActivity.class);
                                finish();
                                startActivity(refresh);

                            }
                        }
                    }
            );


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
//    public void onBackPressed(){
//        FragmentManager fm = getSupportFragmentManager();
//        if (fm.getBackStackEntryCount() > 0) {
//            fm.popBackStack();
//        } else{
//            super.onBackPressed();
//        }
//    }



    public void TosignUp(View view) {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
        finish();

    }







}