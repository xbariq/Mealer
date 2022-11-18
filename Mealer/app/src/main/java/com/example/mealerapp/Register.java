package com.example.mealerapp;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
// these import statements that are not used are for checking if the email is valid with more accuracy
//We are still trying to figure out how we could implement this for the next deliverable


public class Register extends AppCompatActivity {
    EditText theFullName, theEmail, thePassword, thePhone;
    Button theRegisterBtn;
    TextView theLoginBtn;
    FirebaseAuth theAuth;
    ProgressBar progressBar;

    private FirebaseFirestore database;
    private CollectionReference userCollection;
    private FirebaseAuth mAuth;
    private static final String TAG = "RegisterActivity";
    private Account user;

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
        RadioGroup rdg = (RadioGroup) findViewById(R.id.radioGroup);


        database = FirebaseFirestore.getInstance();
        userCollection = database.collection(Constants.USER_COLLECTION);
        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar);

        if (theAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        theLoginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

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
                String role = " ";
                switch (rdg.getCheckedRadioButtonId()) {

                    case R.id.clientbutton:
                        //thisAccount.role= "client";
                        role = "client";
                        break;

                    case R.id.cookbutton:
                        //thisAccount.role= "cook";
                        role = "cook";
                        break;
                }
                user = new Account(role, email);
                registerUser(email, password);
            }
        });

    }

    public void registerUser(String email, String password) {
        progressBar.setVisibility(View.VISIBLE);
        theAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Toast.makeText(Register.this, "user created", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Register.this, " Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }

    public void updateUI(FirebaseUser currentUser) {
        user.setUid(currentUser.getUid());
        userCollection.document(user.getUid())
                .set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent loginIntent = new Intent(Register.this, MainActivity.class);
                            loginIntent.putExtra(Constants.USER_DATA, user);
                            startActivity(loginIntent);
                        } else {
                            Toast.makeText(Register.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}