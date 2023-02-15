package com.example.farmapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;


public class ProductFragment extends Fragment {
    TextView all_card, vegies,fruit, grain, tuber, product_profile_name;
    ImageView product_profile_img;
    Context context;
    private RecyclerView recyclerView;
    private FloatingActionButton addCourseFAB;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private ArrayList<ProductRVModal> courseRVModalArrayList;
    //private GridRecyclerViewHolder courseRVAdapter;
    private ProductsAdapter courseRVAdapter;
    ImageView to_notification;
    Button addToCart;
    String uid;



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
        product_profile_img = view.findViewById(R.id.product_profile_img);
        product_profile_name = view.findViewById(R.id.product_profile_name);
        uid = FirebaseAuth.getInstance().getUid();

        recyclerView = view.findViewById(R.id.rvNumbers);
        courseRVModalArrayList = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        //on below line we are getting database reference.
        databaseReference = firebaseDatabase.getReference("Products");
        addCourseFAB = view.findViewById(R.id.idFABAddCourse);

        to_notification = view.findViewById(R.id.to_notification);
        to_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getContext(), NotificationActivity.class);
                startActivity(i);

            }
        });


        //get user profile
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();

                // UID specific to the provider
                String uid = profile.getUid();

                // Name, email address, and profile photo Url
                String name = profile.getDisplayName();
                String email = profile.getEmail();
                Uri photoUrl = profile.getPhotoUrl();



                if (profile.getPhotoUrl() != null){

                    //product_profile_img.setImageURI(photoUrl);
                    Picasso.get().load(photoUrl).into(product_profile_img);
                }
                if (profile.getDisplayName() != null){
                    product_profile_name.setText(name);
                }


            }
        }



        addCourseFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //opening a new activity for adding a course.
                Intent i = new Intent(getContext(), AddProductActivity.class);
                startActivity(i);
            }
        });




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

        courseRVAdapter = new ProductsAdapter(courseRVModalArrayList, getContext());
       // Toast.makeText(context, courseRVModalArrayList.get(0).toString(), Toast.LENGTH_SHORT).show();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        //recyclerView.setAdapter(new MyRecyclerViewAdapter(1234), this);
        recyclerView.setAdapter(courseRVAdapter);
        getProducts();

        return view;
    }

    private void getProducts() {
        //on below line clearing our list.
        courseRVModalArrayList.clear();
        Query query = databaseReference.orderByChild("userID").equalTo(uid);
        //on below line we are calling add child event listener method to read the data.
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //on below line we are hiding our progress bar.
                //loadingPB.setVisibility(View.GONE);
                //adding snapshot to our array list on below line.
                courseRVModalArrayList.add(snapshot.getValue(ProductRVModal.class));
                //notifying our adapter that data has changed.
                courseRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //this method is called when new child is added we are notifying our adapter and making progress bar visibility as gone.
                //loadingPB.setVisibility(View.GONE);
                courseRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                //notifying our adapter when child is removed.
                courseRVAdapter.notifyDataSetChanged();
                //loadingPB.setVisibility(View.GONE);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //notifying our adapter when child is moved.
                courseRVAdapter.notifyDataSetChanged();
                //loadingPB.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}


