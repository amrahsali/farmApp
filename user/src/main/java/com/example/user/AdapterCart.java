package com.example.user;


import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.MyHolder> {

    Context context;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String uid;

    public AdapterCart(Context context, List<CartRVModal> list) {
        this.context = context;
        this.list = list;
        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getUid();
    }

    List<CartRVModal> list;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_view, parent, false);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Cart");
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
       // final String hisuid = list.get(position).getUid();
        String productImage = list.get(position).getProductImg();
        String productName = list.get(position).getProductName();
        String productDesc = list.get(position).getProductDescription();
        String productPrice = list.get(position).getProductPrice();
        holder.name.setText(productName);
        holder.itemUnit.setText(productDesc);
        holder.price.setText(productPrice);
        holder.addUnit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // Toast.makeText(context, "this is:" + productName, Toast.LENGTH_SHORT).show();
                    }
        });
        holder.reduceUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context, "this is:" + productName, Toast.LENGTH_SHORT).show();
            }
        });
        try {
            Glide.with(context).load(productImage).into(holder.profiletv);
        } catch (Exception e) {
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        ImageView profiletv;
        TextView name, itemUnit, price;
        Button addUnit, reduceUnit;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            profiletv = itemView.findViewById(R.id.cart_product_image);
            name = itemView.findViewById(R.id.cart_product_name);
            itemUnit = itemView.findViewById(R.id.item_unit);
            price = itemView.findViewById(R.id.item_price);
            addUnit = itemView.findViewById(R.id.add_item_unit);
            reduceUnit = itemView.findViewById(R.id.reduce_item_unit);
        }
    }
}
