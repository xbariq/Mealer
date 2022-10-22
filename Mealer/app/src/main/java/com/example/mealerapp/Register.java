package com.example.mealerapp;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
// these import statements that are not used are for checking if the email is valid with more accuracy
//We are still trying to figure out how we could implement this for the next deliverable



public class Register extends AppCompatActivity {
    EditText theFullName,theEmail,thePassword,thePhone;
    Button theRegisterBtn;
    TextView theLoginBtn;
    FirebaseAuth theAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        theFullName = findViewById(R.id.fullName);
        theEmail = findViewById(R.id.Email);
        thePassword = findViewById(R.id.password);
        thePhone = findViewById(R.id.phone);
        theRegisterBtn = findViewById(R.id.registerBtn);
        theLoginBtn = findViewById(R.id.createText);
        theAuth = FirebaseAuth.getInstance();



        progressBar = findViewById(R.id.progressBar);

        if (theAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();


        }


        theRegisterBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = theEmail.getText().toString().trim();
                String password = thePassword.getText().toString().trim();
                //Check if the user left the Email or password blank
                if (TextUtils.isEmpty(password)) {
                    thePassword.setError(" Please enter a password ");
                    return;

                }

                if (TextUtils.isEmpty(email)) {
                    theEmail.setError(" Please enter an Email ");
                    return;
                }
                //Password must be more than 8 characters
                if (password.length() < 8) {
                    thePassword.setError(" Password must contain more than 8 characters ");
                    return;


                }
//                if (TextUtils.isEmpty(phone)) {
//                    mPhone.setError(" Please enter an Phone ");
//                    return;
//                }

                progressBar.setVisibility(View.VISIBLE);
                theAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "user created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Register.this, " Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }


                });
            }

        });
        theLoginBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), Login.class));

            }
        });


    } }