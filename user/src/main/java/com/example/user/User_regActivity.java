package com.example.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;

public class User_regActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {
    String[] languages = { "select language", "english", "hausa" };
    Context context;
    Resources resources;
    BottomNavigationView regNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg);



        Spinner spin = findViewById(R.id.spinnerLang);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, languages);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);


        regNavigationView = findViewById(R.id.regNavigationView);
        regNavigationView.setOnNavigationItemSelectedListener(this);

        regNavigationView.setSelectedItemId(R.id.user_loginMenu);

    }

     userLoginFragment userlogin = new userLoginFragment();
     UserSignupFragment usersignup  = new UserSignupFragment();


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.user_loginMenu:
                getSupportFragmentManager().beginTransaction().replace(R.id.usercontainer1 , userlogin).commit();
                return true;

            case R.id.user_signupMenu:
                getSupportFragmentManager().beginTransaction().replace(R.id.usercontainer1, usersignup).commit();
                return true;
        }
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (languages[position].equals("hausa")){
            context = LocaleHelper.setLocale(User_regActivity.this, "ha");
            resources = context.getResources();
            Intent refresh = new Intent(context, User_regActivity.class);
            Toast.makeText(getApplicationContext(),languages[position] , Toast.LENGTH_SHORT).show();
            finish();
            startActivity(refresh);
        }else if(languages[position].equals("english")){
            //Toast.makeText( LoginActivity.this, "checkbox is unchecked", Toast.LENGTH_SHORT).show();
            context = LocaleHelper.setLocale(User_regActivity.this, "en");
            resources = context.getResources();
            Intent refresh = new Intent(context, User_regActivity.class);
            Toast.makeText(getApplicationContext(),languages[position] , Toast.LENGTH_SHORT).show();
            finish();
            startActivity(refresh);
        }else {
            Toast.makeText(getApplicationContext(),"languages" , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
            Toast.makeText(getApplicationContext(),"languages" , Toast.LENGTH_SHORT).show();


        }

    }
