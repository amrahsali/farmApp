package com.example.user;


import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        final int unit = 0;
        String productImage = list.get(position).getImage();
        String productName = list.get(position).getName();
        String productDesc = list.get(position).getProductDescription();
        String productPrice = list.get(position).getPrice();
        holder.name.setText(productName);
        holder.itemUnit.setText(String.valueOf(unit));
        holder.price.setText(productPrice);
        holder.addUnit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int u = Integer.parseInt(holder.itemUnit.getText().toString());
                        u++;
                        holder.itemUnit.setText(String.valueOf(u));
                       // Toast.makeText(context, "this is:" + productName, Toast.LENGTH_SHORT).show();
                    }
        });
        holder.reduceUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int u = Integer.parseInt(holder.itemUnit.getText().toString());
                if (u != 0){
                    u--;
                    holder.itemUnit.setText(String.valueOf(u));
                }

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
