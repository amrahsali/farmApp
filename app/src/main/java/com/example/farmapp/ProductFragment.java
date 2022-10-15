package com.example.farmapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class ProductFragment extends Fragment {
    TextView all_card, vegies,fruit, grain, tuber;
    Context context;


    public ProductFragment(){
        // require a empty public constructor

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_product, container, false);

        all_card = view.findViewById(R.id.all_card);
        vegies = view.findViewById(R.id.vegies);
        fruit  = view.findViewById(R.id.fruit);
        grain  = view.findViewById(R.id.grain);
        tuber  = view.findViewById(R.id.tuber);




        all_card.setOnClickListener( new View.OnClickListener()


        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), "all", Toast.LENGTH_SHORT).show();
            }
        });

        vegies.setOnClickListener( new View.OnClickListener()


        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), "vegetables", Toast.LENGTH_SHORT).show();
            }
        });
        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Fruit", Toast.LENGTH_SHORT).show();

            }
        });
        grain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "grain", Toast.LENGTH_SHORT).show();

            }

        });
        tuber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Tubers", Toast.LENGTH_SHORT).show();

            }

        });


        return view;

    }
}


