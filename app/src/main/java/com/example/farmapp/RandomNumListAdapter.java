package com.example.farmapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Random;

public class RandomNumListAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private Random random;

    public RandomNumListAdapter(int seed) {
        this.random = new Random(seed);
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.transaction_history_cardview;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.getPayer_name().setText("Kindness");
        holder.getAmount().setText("2000");
        holder.getStatus().setText("Debit");
        holder.getDate().setText("28th sept 2022");
    }

    @Override
    public int getItemCount() {
        return 100;
    }
}