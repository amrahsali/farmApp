package com.example.user;

import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;


public class User_cartFragment extends Fragment implements AdapterCart.OnButtonClickListener {

    Context context;
    private RecyclerView recyclerView;
    TextView total, removeAll;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    private FirebaseAuth mAuth;
    List<CartRVModal> usersList;
    AdapterCart adapterCart;
    String uid;
    Button btn;


    public User_cartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_user_cart, container, false);
        recyclerView = view.findViewById(R.id.cart_rv);
        total = view.findViewById(R.id.total_prize);
        removeAll = view.findViewById(R.id.remove_all);

        firebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        uid = FirebaseAuth.getInstance().getUid();
        //on below line we are getting database reference.

        reference = FirebaseDatabase.getInstance().getReference("Cart");
        usersList = new ArrayList<>();
        adapterCart = new AdapterCart(usersList, getContext());
        // Toast.makeText(context, courseRVModalArrayList.get(0).toString(), Toast.LENGTH_SHORT).show();


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        //recyclerView.setAdapter(new MyRecyclerViewAdapter(1234), this);
        recyclerView.setAdapter(adapterCart);

//        ArrayList<String> idsList = new ArrayList<>();
//
//        for (int i = 0; i < recyclerView.getAdapter().getItemCount(); i++) {
//            CartRVModal item = (CartRVModal) recyclerView.getAdapter().getItemId(i);
//            String itemId = item.getId();
//            idsList.add(itemId);
//        }

        getAllUsers();

          List<Requestmodal> products;

        products= new ArrayList<>();


        btn = view.findViewById(R.id.request);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             //   products.get(1).getName();
               // products.get(1).getImage();
                //products.get(1).getPrice();
            }
        });



        return view;
    }



    private void getAllUsers() {
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                usersList.clear();
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                    ModelProducts modelUsers = dataSnapshot1.getValue(ModelProducts.class);
//                    //Toast.makeText(getActivity(), modelUsers.getUid(), Toast.LENGTH_SHORT).show();
//                    if (modelUsers.getUid() != null ) {
//                        usersList.add(modelUsers);
//
//                    }
//                    adapterProducts = new AdapterProducts(getActivity(), usersList);
//                    recyclerView.setAdapter(adapterProducts);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        usersList.clear();
        Query query = reference.orderByChild("uid").equalTo(uid);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //on below line we are hiding our progress bar.
                //loadingPB.setVisibility(View.GONE);
                //adding snapshot to our array list on below line.
                usersList.add(snapshot.getValue(CartRVModal.class));
                //notifying our adapter that data has changed.
                adapterCart.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //this method is called when new child is added we are notifying our adapter and making progress bar visibility as gone.
                //loadingPB.setVisibility(View.GONE);
                adapterCart.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                //notifying our adapter when child is removed.
                adapterCart.notifyDataSetChanged();
                //loadingPB.setVisibility(View.GONE);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //notifying our adapter when child is moved.
                adapterCart.notifyDataSetChanged();
                //loadingPB.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public void onButtonClick(int position) {
        // Retrieve the ID of the item at the given position
        long itemId = adapterCart.getItemId(position);
        Toast.makeText(context, String.valueOf(itemId), Toast.LENGTH_SHORT).show();
    }
}