package com.example.farmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignupActivity extends AppCompatActivity {
    EditText phoneNumber;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        phoneNumber.findViewById(R.id.phone_number);
    }




    public void Login(View view) {
        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void ToCreate(View view) {
    }

    public void Tosend(View view) {

        phone = phoneNumber.getText().toString();

       // PhoneAuthOptions options =
            //    PhoneAuthOptions.newBuilder(mAuth)
            //            .setPhoneNumber(phoneNumber)       // Phone number to verify
           //             .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
           //             .setActivity(this)                 // Activity (for callback binding)
           //             .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
          //              .build();
     //   PhoneAuthProvider.verifyPhoneNumber(options);

    }
}