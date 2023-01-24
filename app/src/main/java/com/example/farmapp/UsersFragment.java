package com.example.farmapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class UsersFragment extends Fragment {

    Button button;

    private EditText userNameEdt, passwordEdt, fullname;
    private Button registerBtn;
    private FirebaseAuth mAuth;
    private ProgressBar loadingPB;

    public UsersFragment() {
        // require a empty public constructor

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container2, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.activity_signup, container2, false);

        userNameEdt = view.findViewById(R.id.phone_number);
        passwordEdt = view.findViewById(R.id.user_password);
        fullname = view.findViewById(R.id.fullname);
        loadingPB = view.findViewById(R.id.idPBLoading);
        registerBtn = view.findViewById(R.id.create);
        mAuth = FirebaseAuth.getInstance();


        // adding click listener for register button.
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hiding our progress bar.
                loadingPB.setVisibility(View.VISIBLE);

                // getting data from our edit text.
                String userName = userNameEdt.getText().toString();
                String pwd = passwordEdt.getText().toString();
                String fullN = fullname.getText().toString();

                // checking if the password and confirm password is equal or not.
                if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(pwd)) {

                    // checking if the text fields are empty or not.
                    Toast.makeText(getContext(), "Please enter your credentials..", Toast.LENGTH_SHORT).show();
                } else {

                    // on below line we are creating a new user by passing email and password.
                    mAuth.createUserWithEmailAndPassword(userName, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // on below line we are checking if the task is success or not.
                            if (task.isSuccessful()) {

                                FirebaseUser user = mAuth.getCurrentUser();
                                String email = user.getEmail();
                                String uid = user.getUid();
                                HashMap<Object, String> hashMap = new HashMap<>();
                                hashMap.put("email", email);
                                hashMap.put("uid", uid);
                                hashMap.put("fullname", fullN);
                                hashMap.put("image", "");
                                hashMap.put("phone", "");
                                hashMap.put("homeAddress", "");
                                hashMap.put("farmAddress", "");
                                hashMap.put("products", "");

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference reference = database.getReference("Farmers");
                                reference.child(uid).setValue(hashMap);


                                // in on success method we are hiding our progress bar and opening a login activity.
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(getContext(), "User Registered..", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getContext(), LoginActivity.class);
                                startActivity(i);
                                requireActivity().finish();
                            } else {

                                // in else condition we are displaying a failure toast message.
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(getContext(), "Fail to register user..", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                }
            }
        });

        return view;
    }
}