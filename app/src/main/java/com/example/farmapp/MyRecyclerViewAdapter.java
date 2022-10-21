package com.example.farmapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Random;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<GridRecyclerViewHolder> {

    private Random random;
    Context context;

    public MyRecyclerViewAdapter(int seed, Context context) {
        this.random = new Random(seed);
        this.context = context;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.product_cardview;
    }

    @NonNull
    @Override
    public GridRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new GridRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridRecyclerViewHolder holder, int position) {
        holder.getProduct_name().setText("oranges");
        holder.getProduct_description().setText("reaap oranges for sell");
        holder.getProduct_price().setText("N10,000");

//        Drawable drawable = context.getResources().getDrawable(R.drawable.oranges);
//        Drawable drawable1 = context.getResources().getDrawable(R.drawable.oranges);
//        holder.getProduct_image().setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return 100;
    }
}
