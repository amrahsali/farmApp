package com.example.farmapp;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class AddProductActivity extends AppCompatActivity {

    // creating variables for our button, edit text,
    // firebase database, database reference, progress bar.
    private Button addCourseBtn;
    int SELECT_PICTURE = 200;
    private TextInputEditText courseNameEdt, courseDescEdt, coursePriceEdt;
    Button productImgBtn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private ProgressBar loadingPB;
    private StorageReference mStorageref;
    private String courseID;
    Uri selectedImageUri, imageuri;
    ImageView productImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        // initializing all our variables.
        addCourseBtn = findViewById(R.id.idBtnAddCourse);
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseDescEdt = findViewById(R.id.idEdtCourseDescription);
        coursePriceEdt = findViewById(R.id.idEdtCoursePrice);
        productImgBtn = findViewById(R.id.idEdtCourseImageLink);
        //productImageView = findViewById(R.id.product_image);
        loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        // on below line creating our database reference.
        databaseReference = firebaseDatabase.getReference(Objects.requireNonNull(mAuth.getCurrentUser()).getUid() +"Products");
        mStorageref = FirebaseStorage.getInstance().getReference("Upload Photos");

        productImgBtn.setOnClickListener(new View.OnClickListener() {
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

        // adding click listener for our add course button.
        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);
                // getting data from our edit text.
                String courseName = courseNameEdt.getText().toString();
                String courseDesc = courseDescEdt.getText().toString();
                String coursePrice = coursePriceEdt.getText().toString();
                Uri productImage = imageuri;
                String productUri = productImage.toString();
                getContentResolver().takePersistableUriPermission(imageuri, (Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION));
                //String courseImg = productImgBtn.getText().toString();

                courseID = courseName;
                // on below line we are passing all data to our modal class.
                ProductRVModal courseRVModal = new ProductRVModal(courseID, courseName, courseDesc, coursePrice, productUri);
                // on below line we are calling a add value event
                // to pass data to firebase database.
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // on below line we are setting data in our firebase database.
                        databaseReference.child(courseID).setValue(courseRVModal);
                        // displaying a toast message.
                        Toast.makeText(AddProductActivity.this, "Product Added..", Toast.LENGTH_SHORT).show();
                        // starting a main activity.
                        startActivity(new Intent(AddProductActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // displaying a failure message on below line.
                        Toast.makeText(AddProductActivity.this, "Failed to add Product..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
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
                    //Picasso.get().load(imageuri).into(productImageView);
                    //IVPreviewImage.setImageURI(selectedImageUri);
                }
            }
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));

    }

    private void uploadfile() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading");
        progressDialog.show();


        if (imageuri !=null){
            StorageReference  filereference  = mStorageref.child(System.currentTimeMillis()+
                    "."+getFileExtension(imageuri));

            filereference.putFile(imageuri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                            Toast.makeText(AddProductActivity.this, "Upload Successfully", Toast.LENGTH_SHORT).show();
                            Upload upload = new Upload(Objects.requireNonNull(mAuth.getCurrentUser()).getUid(),taskSnapshot.getMetadata().getReference().getDownloadUrl()
                                    .toString());
                            progressDialog.show();




                            String  uploadId = databaseReference.push().getKey();
                            databaseReference.child(uploadId).setValue(upload);
                            progressDialog.setCanceledOnTouchOutside(false);
                            progressDialog.dismiss();




                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                            progressDialog.setCanceledOnTouchOutside(false);
                            progressDialog.setMessage("Uploaded  " +(int)progress+"%");



                        }
                    });

        }else
            Toast.makeText(this, "Please Select a Image", Toast.LENGTH_SHORT).show();





    }
}