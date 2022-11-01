package com.example.farmapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FarmersLoginFragment extends Fragment {
 Button button;

    public FarmersLoginFragment() {
        // require a empty public constructor


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container2, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_farmers_login, container2, false);
        button = view.findViewById(R.id.farmersbtn);
       button.setOnClickListener(new View.OnClickListener() {


           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getActivity(), MainActivity.class);
               startActivity(intent);
           }
       });
        return view;

    }
}