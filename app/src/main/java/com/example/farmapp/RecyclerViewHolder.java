package com.example.farmapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder  extends RecyclerView.ViewHolder{
    private TextView payer_name, amount, status, date;
    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        payer_name = itemView.findViewById(R.id.payer_name);
        amount = itemView.findViewById(R.id.amount);
        status = itemView.findViewById(R.id.status);
        date = itemView.findViewById(R.id.date);

    }

    public TextView getPayer_name(){
        return payer_name;
    }

    public TextView getAmount(){
        return amount;
    }
    public TextView getStatus(){
        return status;
    }
    public TextView getDate(){
        return date;
    }
}
