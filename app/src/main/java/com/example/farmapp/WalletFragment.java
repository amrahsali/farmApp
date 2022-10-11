package com.example.farmapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class WalletFragment extends Fragment {

    public WalletFragment() {
        // require a empty public constructor
    }
   Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        button.findViewById(R.id.withdraw);
//        button.setOnClickListener(
//
//        );
        return inflater.inflate(R.layout.fragment_wallet, container, false);


    }




}