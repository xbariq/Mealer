package com.example.mealerapp;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {
    EditText theEmail,thePassword;
    Button theLoginBtn;
    TextView theCreateBtn;
    FirebaseAuth theAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        theEmail= findViewById(R.id.Email);
        thePassword= findViewById(R.id.password);
        theLoginBtn= findViewById(R.id.log);
        theCreateBtn= findViewById(R.id.createText);
        theAuth= FirebaseAuth.getInstance();

        theLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String email = theEmail.getText().toString().trim();
                String password = thePassword.getText().toString().trim();
                //Check if the user left the Email or password blank


                if (TextUtils.isEmpty(password)){
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



                theAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "You are logged in! ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(Login.this, "Please verify your input! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                        }
                }
                });



            }
        });

        theCreateBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), Register.class));

            }
        });

    }

}