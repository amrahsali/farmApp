package com.example.user;

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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class UserSignupFragment extends Fragment {

    private EditText userNameEdt, passwordEdt, confirmPwdEdt;
    private TextView loginTV;
    private Button registerBtn;
    private FirebaseAuth mAuth;
    private ProgressBar loadingPB;

    public UserSignupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // mAuth = FirebaseAuth.getInstance();
        //FirebaseApp.initializeApp(Context)
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_signup, container, false);
        userNameEdt = view.findViewById(R.id.email);
        passwordEdt = view.findViewById(R.id.password);
        loadingPB = view.findViewById(R.id.idPBLoading);
        FirebaseApp.initializeApp(getContext());
        //loginTV = findViewById(R.id.idTVLoginUser);
        registerBtn = view.findViewById(R.id.btnMain);
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

                // checking if the password and confirm password is equal or not.
                if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(pwd)) {

                    // checking if the text fields are empty or not.
                    Toast.makeText(getActivity(), "Please enter your credentials..", Toast.LENGTH_SHORT).show();
                    loadingPB.setVisibility(View.GONE);
                } else {

                    // on below line we are creating a new user by passing email and password.
                    mAuth.createUserWithEmailAndPassword(userName, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // on below line we are checking if the task is success or not.
                            if (task.isSuccessful()) {

                                // in on success method we are hiding our progress bar and opening a login activity.
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "User Registered..", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getActivity(), User_regActivity.class);
                                startActivity(i);
                                requireActivity().finish();
                            } else {

                                // in else condition we are displaying a failure toast message.
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "Fail to register user..", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                }
            }
        });

        return view;
    }
}