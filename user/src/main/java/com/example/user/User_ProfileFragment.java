package com.example.user;

import static android.app.Activity.RESULT_OK;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;


public class User_ProfileFragment extends Fragment {

    private FirebaseAuth mAuth;
    Button log_out_btn, update_btn;
    EditText username, phoneNumber, homeAddress, farmAddress, emailad;
    ImageView profileimg;
    int SELECT_PICTURE = 200;
    Uri selectedImageUri, imageuri;

    public User_ProfileFragment() {
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
        View view = inflater.inflate(R.layout.fragment_user__profile, container, false);
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance();
        log_out_btn = view.findViewById(R.id.logoutbutn);
        update_btn = view.findViewById(R.id.updatebutn);
        username = view.findViewById(R.id.username);
        phoneNumber = view.findViewById(R.id.userPhone);
        homeAddress = view.findViewById(R.id.userHomeAddress);
        profileimg = view.findViewById(R.id.userprofile);
        emailad = view.findViewById(R.id.user_email);

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
                String phone = profile.getPhoneNumber();

                if (!name.isEmpty()){
                    username.setText(name);
                    // profileimg.setImageURI(photoUrl);
                    //Picasso.with(getContext()).load(photoUrl).into(imageView);
                    Picasso.get().load(photoUrl).into(profileimg);
                    emailad.setText(email);
                    phoneNumber.setText(phone);
                }


            }
        }

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
                        // getting the url of image uploaded
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isSuccessful()) ;
                        String downloadUri = uriTask.getResult().toString();
                        if (uriTask.isSuccessful()) {

                            String fulname = username.getText().toString();
                            String emailaddress = emailad.getText().toString();
                            String phoneNumberData = phoneNumber.getText().toString();

                            // Toast.makeText(getContext(), "Profile updated", Toast.LENGTH_LONG).show();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(fulname)
                                    .setPhotoUri(Uri.parse(downloadUri))
                                    .build();

                            assert user != null;
                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "User profile updated.");
                                                Toast.makeText(getContext(), "User profile updated.", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                            user.updateEmail(emailaddress)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "User email address updated.");
                                            }
                                        }
                                    });
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //loadingPB.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_LONG).show();
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
                Intent i = new Intent(getContext(), User_regActivity.class);
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