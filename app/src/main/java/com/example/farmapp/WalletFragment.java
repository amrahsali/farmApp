package com.example.farmapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class WalletFragment extends Fragment {
    // Add RecyclerView member
    private RecyclerView recyclerView;
    public Button fundWallet, withdraw;

    public WalletFragment() {
        // require a empty public constructor
    }
   Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //return inflater.inflate(R.layout.fragment_wallet, container, false);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);

        fundWallet = view.findViewById(R.id.fund_wallet);
        fundWallet.setOnClickListener( new View.OnClickListener()





        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), "pop up fundwallet dialog", Toast.LENGTH_SHORT).show();
            }
        });
        // Add the following lines to create RecyclerView
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new RandomNumListAdapter(1234));

        return view;


    }

    public void fundWallet(View view) {
        Toast.makeText(getActivity(), "pop up fund dialog", Toast.LENGTH_SHORT).show();
    }
}