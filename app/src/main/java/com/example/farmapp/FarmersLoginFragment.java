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

import java.util.Objects;


public class FarmersLoginFragment extends Fragment {
 Button button;
    private EditText userNameEdt, passwordEdt;
    private Button loginBtn;
    private TextView newUserTV;
    private FirebaseAuth mAuth;
    private ProgressBar loadingPB;

    public FarmersLoginFragment() {
        // require a empty public constructor


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container2, Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_farmers_login, container2, false);

        // initializing all our variables.
        userNameEdt = view.findViewById(R.id.farmer_id);
        passwordEdt = view.findViewById(R.id.farmer_password);
       // newUserTV = findViewById(R.id.idTVNewUser);
        mAuth = FirebaseAuth.getInstance();
        loadingPB = view.findViewById(R.id.idPBLoading);
        button = view.findViewById(R.id.farmersbtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hiding our progress bar.
                loadingPB.setVisibility(View.VISIBLE);
                // getting data from our edit text on below line.
                String email = userNameEdt.getText().toString();
                String password = passwordEdt.getText().toString();
                // on below line validating the text input.
                if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                    Toast.makeText(getActivity(), "Please enter your credentials..", Toast.LENGTH_SHORT).show();
                    return;
                }
                // on below line we are calling a sign in method and passing email and password to it.
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // on below line we are checking if the task is success or not.
                        if (task.isSuccessful()) {
                            // on below line we are hiding our progress bar.
                            loadingPB.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), "Login Successful..", Toast.LENGTH_SHORT).show();
                            // on below line we are opening our mainactivity.
                            Intent i = new Intent(getActivity(), MainActivity.class);
                            startActivity(i);
                            requireActivity().finish();
                        } else {
                            // hiding our progress bar and displaying a toast message.
                            loadingPB.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), "Please enter valid user credentials..", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        // in on start method checking if
        // the user is already sign in.
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // if the user is not null then we are
            // opening a main activity on below line.
            Intent i = new Intent(getActivity(), MainActivity.class);
            startActivity(i);
            requireActivity().finish();
        }
    }

}