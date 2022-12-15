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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LoginActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener{

    String[] languages = { "select language", "english", "hausa" };

    CheckBox checkBox;
    Context context;
    Resources resources;
    BottomNavigationView loginNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Spinner spin = findViewById(R.id.lang_spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, languages);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

       // checkBox = findViewById(R.id.checkboxId);
//            checkBox.setOnClickListener(
//                    new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            if (checkBox.isChecked()) {
//                                context = LocaleHelper.setLocale(LoginActivity.this, "ha");
//                                resources = context.getResources();
//                                Intent refresh = new Intent(context, LoginActivity.class);
//                                finish();
//                                startActivity(refresh);
//
//                                //Toast.makeText(this, "checkbox is checked", Toast.LENGTH_SHORT).show();
//                                Toast.makeText(LoginActivity.this, "checkbox is checked", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText( LoginActivity.this, "checkbox is unchecked", Toast.LENGTH_SHORT).show();
//                                context = LocaleHelper.setLocale(LoginActivity.this, "en");
//                                resources = context.getResources();
//                                Intent refresh = new Intent(context, LoginActivity.class);
//                                finish();
//                                startActivity(refresh);
//
//                            }
//                        }
//                    }
//            );


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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (languages[position].equals("hausa")){
            context = LocaleHelper.setLocale(LoginActivity.this, "ha");
            resources = context.getResources();
            Intent refresh = new Intent(context, LoginActivity.class);
            Toast.makeText(getApplicationContext(),languages[position] , Toast.LENGTH_SHORT).show();
            finish();
            startActivity(refresh);
        }else if(languages[position].equals("english")){
            //Toast.makeText( LoginActivity.this, "checkbox is unchecked", Toast.LENGTH_SHORT).show();
            context = LocaleHelper.setLocale(LoginActivity.this, "en");
            resources = context.getResources();
            Intent refresh = new Intent(context, LoginActivity.class);
            Toast.makeText(getApplicationContext(),languages[position] , Toast.LENGTH_SHORT).show();
            finish();
            startActivity(refresh);
        }else {
            Toast.makeText(getApplicationContext(),"languages" , Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(),"languages" , Toast.LENGTH_SHORT).show();

    }
}