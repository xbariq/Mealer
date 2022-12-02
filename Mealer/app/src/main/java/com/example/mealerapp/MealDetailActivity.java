package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MealDetailActivity extends AppCompatActivity {

    TextView mealName, mealType, mealDescription, cuisineType, cookName, cookRating;
    Button requestPurchase;
    MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        mealName= findViewById(R.id.mealName);
        mealType= findViewById(R.id.mealType);
        mealDescription= findViewById(R.id.mealDescription);
        cuisineType= findViewById(R.id.cuisineType);
        cookName= findViewById(R.id.cookName);
        cookRating= findViewById(R.id.cookRating);

        menuItem= (MenuItem) getIntent().getSerializableExtra(Constants.MEAL_DATA);
        setData(menuItem);
    }

    private void setData (MenuItem menuItem){
        mealName.setText(menuItem.getMeal());
        mealType.setText(menuItem.getMealType());
        mealDescription.setText(menuItem.getDescription());
        cuisineType.setText(menuItem.getCuisineType());
        getUserData(menuItem.getCookId());
    }

    private void getUserData(String cookId) {
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection(Constants.USER_COLLECTION).document(cookId)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            Account userData= task.getResult().toObject(Account.class);
                            cookName.setText(userData.getUsername());
                        }
                    }
                });

    }

}