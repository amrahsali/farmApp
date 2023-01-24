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

public class AdapterProducts extends RecyclerView.Adapter<AdapterProducts.MyHolder> {

    Context context;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String uid;

    public AdapterProducts(Context context, List<ModelProducts> list) {
        this.context = context;
        this.list = list;
        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getUid();
    }

    List<ModelProducts> list;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_product_cardview, parent, false);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Cart");
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        final String hisuid = list.get(position).getUid();
        String productImage = list.get(position).getProductImg();
        String productName = list.get(position).getProductName();
        String productDesc = list.get(position).getProductDescription();
        String productPrice = list.get(position).getProductPrice();
        holder.name.setText(productName);
        holder.descr.setText(productDesc);
        holder.price.setText(productPrice);
        holder.addToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // updating our image url into the realtime database
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("image", productImage);
                        hashMap.put("name", productName);
                        hashMap.put("description", productDesc);
                        hashMap.put("price", productPrice);
                        hashMap.put("uid", hisuid);

                        databaseReference.child(firebaseAuth.getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "Profile updated.");
                                Toast.makeText(holder.itemView.getContext(), productName + " has been added to cart", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                //loadingPB.setVisibility(View.GONE);
                                Toast.makeText(holder.itemView.getContext(), "Failed", Toast.LENGTH_LONG).show();
                            }
                        });
                       // Toast.makeText(context, "this is:" + productName, Toast.LENGTH_SHORT).show();
                    }
        }
        );
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
        TextView name, descr, price;
        Button addToCart;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            profiletv = itemView.findViewById(R.id.product_image);
            name = itemView.findViewById(R.id.product_name);
            descr = itemView.findViewById(R.id.product_description);
            price = itemView.findViewById(R.id.product_price);
            addToCart = itemView.findViewById(R.id.cart_add);

        }
    }
}
