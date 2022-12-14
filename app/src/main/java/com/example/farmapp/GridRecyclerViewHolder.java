package com.example.farmapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GridRecyclerViewHolder extends RecyclerView.Adapter<GridRecyclerViewHolder.ViewHolder> {
    // creating variables for our list, context, interface and position.
    private ArrayList<ProductRVModal> courseRVModalArrayList;
    private Context context;
    private CourseClickInterface courseClickInterface;

    TextView product_name, Product_price, Product_description;
    ImageView product_image;
    int lastPos = -1;

    // creating a constructor.
    public GridRecyclerViewHolder(ArrayList<ProductRVModal> courseRVModalArrayList, Context context) {
        this.courseRVModalArrayList = courseRVModalArrayList;
        this.context = context;
       // this.courseClickInterface = courseClickInterface;
    }

    @NonNull
    @Override
    public GridRecyclerViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflating our layout file on below line.
        View view = LayoutInflater.from(context).inflate(R.layout.product_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridRecyclerViewHolder.ViewHolder holder, int position) {
        // setting data to our recycler view item on below line.
        ProductRVModal productRVModal = courseRVModalArrayList.get(position);
        holder.courseTV.setText(productRVModal.getProductName());
        holder.coursePriceTV.setText("N. " + productRVModal.getProductPrice());
        Picasso.get().load(productRVModal.getProductImg()).into(holder.courseIV);
        // adding animation to recycler view item on below line.
        setAnimation(holder.itemView, position);
        holder.courseIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseClickInterface.onCourseClick(position);
            }
        });
    }

    private void setAnimation(View itemView, int position) {
        if (position > lastPos) {
            // on below line we are setting animation.
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return courseRVModalArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // creating variable for our image view and text view on below line.
        private ImageView courseIV;
        private TextView courseTV, coursePriceTV;
        private TextView product_name, Product_price, Product_description;
        ImageView product_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing all our variables on below line.
//            courseIV = itemView.findViewById(R.id.idIVCourse);
//            courseTV = itemView.findViewById(R.id.idTVCOurseName);
//            coursePriceTV = itemView.findViewById(R.id.idTVCousePrice);

            product_name = itemView.findViewById(R.id.product_name);
            product_image = itemView.findViewById(R.id.product_image);
            Product_description = itemView.findViewById(R.id.product_description);
            Product_price = itemView.findViewById(R.id.product_price);
        }
    }

    // creating a interface for on click
    public interface CourseClickInterface {
        void onCourseClick(int position);
    }
}
//    private TextView product_name, Product_price, Product_description;
//    ImageView product_image;
//    public GridRecyclerViewHolder(@NonNull View itemView) {
//        super(itemView);
//
//        product_name = itemView.findViewById(R.id.product_name);
//        product_image = itemView.findViewById(R.id.product_image);
//        Product_description = itemView.findViewById(R.id.product_description);
//        Product_price = itemView.findViewById(R.id.product_price);
//
//
//    }


//    public TextView getProduct_name(){
//        return product_name;
//    }
//
//    public TextView getProduct_price(){
//        return Product_price;
//    }
//
//    public ImageView getProduct_image(){
//        return getProduct_image();
//    }
//    public TextView getProduct_description(){
//        return Product_description;
//    }

