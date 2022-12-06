package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private TextView role;
    private Account userData;
    FirebaseFirestore firestore;
    Button btnSeeComplaints, btnAddMeal, btnSearchMeal, btnSeePurchaseRequest;
    //Firebase


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        role = findViewById(R.id.role_textview);
        btnSeeComplaints = findViewById(R.id.btnSeeComplaints);
        btnAddMeal= findViewById(R.id.btnAddMeal);
        btnSearchMeal= findViewById(R.id.btnSearchMeals);
        btnSeePurchaseRequest= findViewById(R.id.btnSeePurchaseRequests);
        getUserData();

        btnSeeComplaints.setOnClickListener(v -> {
            Intent intent = new Intent(this, ComplaintListActivity.class);
            startActivity(intent);
        });
        btnAddMeal.setOnClickListener(view -> {
            Intent intent =new Intent(this, AddMealActivity.class );
            startActivity (intent);
        });

        btnSearchMeal.setOnClickListener(view -> {
            Intent intent= new Intent (this, SearchMealActivity.class);
            startActivity(intent);
        });
        btnSeePurchaseRequest.setOnClickListener(view ->{
            if (userData.getRole().equals(Constants.COOK_ROLE)){
                Intent intent= new Intent (this, PurchaseRequestListActivity.class);
                startActivity(intent);
            }
            else{
                Intent intent= new Intent (this, ClientPurchaseRequestActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getUserData() {
        firestore = FirebaseFirestore.getInstance();

        firestore.collection(Constants.USER_COLLECTION).document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            try {
                                userData = task.getResult().toObject(Account.class);
                                role.setText(userData.getRole());

                                if(userData.getRole().equals(Constants.ADMINISTRATOR_ROLE)){
                                    btnSeeComplaints.setVisibility(View.VISIBLE);
                                }

                                if (userData.getRole().equals (Constants.COOK_ROLE)){
                                    btnAddMeal.setVisibility(View.VISIBLE);
                                    btnSeePurchaseRequest.setVisibility(View.VISIBLE);
                                }

                                if(!userData.isActive()) {
                                    showAlert();
                                }

                                if (userData.getRole().equals (Constants.CLIENT_ROLE)){
                                    btnSearchMeal.setVisibility(View.VISIBLE);
                                    btnSeePurchaseRequest.setVisibility(View.VISIBLE);
                                }

                            }catch(Exception ex){

                            }
                        }
                    }
                });

    }

    private void showAlert(){
        AlertDialog.Builder  alertDialog = new  AlertDialog.Builder(this);
        alertDialog.setMessage("You are suspended");
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Register.class));
                finish();
            }
        });
        alertDialog.show();
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Register.class));
        finish();
    }
}
