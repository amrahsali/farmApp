package com.example.farmapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class UsersFragment extends Fragment {

    Button button;

    private EditText userNameEdt, passwordEdt, confirmPwdEdt;
    private TextView loginTV;
    private Button registerBtn;
    private FirebaseAuth mAuth;
    private ProgressBar loadingPB;

    public UsersFragment() {
        // require a empty public constructor

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container2, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.activity_signup, container2, false);
        
        button = view.findViewById(R.id.create_account);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "User's dashboard not available", Toast.LENGTH_SHORT).show();
            //    Intent intent = new Intent(getActivity(), MainActivity.class);
              //  startActivity(intent);
            }
        });

        return view;
    }
}