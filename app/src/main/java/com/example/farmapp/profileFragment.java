package com.example.farmapp;

import static android.app.Activity.RESULT_OK;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;


public class profileFragment extends Fragment  {
    private FirebaseAuth mAuth;
    Button log_out_btn, update_btn;
    EditText username, phoneNumber, homeAddress, farmAddress, emailad;
    ImageView profileimg;
    int SELECT_PICTURE = 200;
    Uri selectedImageUri, imageuri;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    MediaPlayer mMediaPlayer;
    TextView name,email;



    public profileFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance();

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        log_out_btn = view.findViewById(R.id.logoutbutn);
        update_btn = view.findViewById(R.id.updatebutn);
        username = view.findViewById(R.id.username);
        phoneNumber = view.findViewById(R.id.userPhone);
        homeAddress = view.findViewById(R.id.userHomeAddress);
        farmAddress = view.findViewById(R.id.userFarmAddress);
        profileimg = view.findViewById(R.id.userprofile);
        emailad = view.findViewById(R.id.user_email);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Farmers");


        name = view.findViewById(R.id.name);

        mMediaPlayer = MediaPlayer.create(getContext(), R.raw.suna);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.start();
            }
        });


//        email = view.findViewById(R.id.email);
//
//        mMediaPlayer = MediaPlayer.create(getContext(), R.raw.imel);
//        email.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mMediaPlayer.stop();
//            }
//        });

        profileimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create an instance of the
                // intent of the type image

                if (Build.VERSION.SDK_INT <19){
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
                } else {
                    Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("image/*");
                    // pass the constant to compare it
                    // with the returned requestCode
                    startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
                }

            }
        });


        //get user profile
        FirebaseUser user = mAuth.getCurrentUser();

        assert user != null;
        Query query = databaseReference.orderByChild("email").equalTo(user.getEmail());
        //Toast.makeText(getActivity(), firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String name = "" + dataSnapshot1.child("fullname").getValue();
                    String emaill = "" + dataSnapshot1.child("email").getValue();
                    String image = "" + dataSnapshot1.child("image").getValue();
                    String phone = "" + dataSnapshot1.child("phone").getValue();
                    String homeAdd = "" + dataSnapshot1.child("homeAddress").getValue();
                    String farmAdd = "" + dataSnapshot1.child("farmAddress").getValue();
                    username.setText(name);
                    emailad.setText(emaill);
                    phoneNumber.setText(phone);
                    homeAddress.setText(homeAdd);
                    farmAddress.setText(farmAdd);
                    try {
                        Glide.with(getActivity()).load(image).into(profileimg);
                    } catch (Exception e) {

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // displaying a toast message on user update profile.
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String timestamp = String.valueOf(System.currentTimeMillis());
                String filepathname = "Profile/" + "images" + timestamp;
                Bitmap bitmap = ((BitmapDrawable) profileimg.getDrawable()).getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] data = byteArrayOutputStream.toByteArray();

                StorageReference storageReference1 = FirebaseStorage.getInstance().getReference().child(filepathname);
                storageReference1.putBytes(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                         //getting the url of image uploaded
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isSuccessful()) ;
                        String downloadUri = uriTask.getResult().toString();
                        if (uriTask.isSuccessful()) {

                            String fulname = username.getText().toString();
                            String emailaddress = emailad.getText().toString();
                            String phoneNumberData = phoneNumber.getText().toString();
                            String farmaddress = farmAddress.getText().toString();
                            String homeaddress = homeAddress.getText().toString();


                            // Toast.makeText(getContext(), "Profile updated", Toast.LENGTH_LONG).show();

                            // updating our image url into the realtime database
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("image", downloadUri.toString());
                            hashMap.put("name", fulname);
                            hashMap.put("phone", phoneNumberData);
                            hashMap.put("email", emailaddress);
                            hashMap.put("farmAddress", farmaddress);
                            hashMap.put("homeAddress", homeaddress);

                            databaseReference.child(user.getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "Profile updated.");
                                    Toast.makeText(getContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                                }
                             }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    //loadingPB.setVisibility(View.GONE);
                                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_LONG).show();
                                }
                            });
                         }
                    }
                });
            }
        });




        // displaying a toast message on user logged out inside on click.
        log_out_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //opening a new activity for adding a course.
                Toast.makeText(getContext(), "User Logged Out", Toast.LENGTH_LONG).show();
                // on below line we are signing out our user.
                mAuth.signOut();
                // on below line we are opening our login activity.
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });


        return view;
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout

                    imageuri = data.getData();
                    Picasso.get().load(imageuri).into(profileimg);
                    //IVPreviewImage.setImageURI(selectedImageUri);
                }
            }
        }
    }

}