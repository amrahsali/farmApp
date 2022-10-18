package com.example.farmapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridRecyclerViewHolder extends RecyclerView.ViewHolder{
    private TextView product_name, Product_price, Product_description;
    ImageView product_image;
    public GridRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        product_name = itemView.findViewById(R.id.product_name);
        product_image = itemView.findViewById(R.id.product_image);
        Product_description = itemView.findViewById(R.id.product_description);
        Product_price = itemView.findViewById(R.id.product_price);


    }


    public TextView getProduct_name(){
        return product_name;
    }

    public TextView getProduct_price(){
        return Product_price;
    }

    public ImageView getProduct_image(){
        return getProduct_image();
    }
    public TextView getProduct_description(){
        return Product_description;
    }
}
