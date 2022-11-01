package com.example.farmapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class UsersFragment extends Fragment {

    Button button;
    public UsersFragment() {
        // require a empty public constructor

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container2, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_users_login, container2, false);
        
        button = view.findViewById(R.id.button1);

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