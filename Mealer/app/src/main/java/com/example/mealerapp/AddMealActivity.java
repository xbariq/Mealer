package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddMealActivity extends AppCompatActivity {
    EditText addmeal;
    Button add;
    EditText description;
    Button seemenu;
    CheckBox offeredmeal;
    Button seeofferedmenu;
    EditText mealtype;
    EditText cuisinetype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        addmeal= findViewById(R.id.addameal);
        add=findViewById(R.id.btnadd);
        description= findViewById(R.id.description);
        seemenu= findViewById (R.id.btnseemenu);
        offeredmeal= findViewById(R.id.addOffered);
        seeofferedmenu= findViewById(R.id.btnseeOffered);
        mealtype= findViewById(R.id.mealType);
        cuisinetype= findViewById(R.id.cuisineType);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMeal();
            }
        });

        seemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent (AddMealActivity.this, MenuActivity.class);
                intent.putExtra(Constants.OFFERED, false);
                startActivity(intent);
            }
        });

        seeofferedmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent (AddMealActivity.this, MenuActivity.class);
                intent.putExtra(Constants.OFFERED, true);
                startActivity (intent);
            }
        });



    }

    private void addMeal(){
        if (validateData()){
            String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
            MenuItem menuItem= new MenuItem(addmeal.getText().toString(), description.getText().toString(),userId, offeredmeal.isChecked(), mealtype.getText().toString(), cuisinetype.getText().toString());

            FirebaseFirestore firebaseFirestore= FirebaseFirestore.getInstance();

            DocumentReference documentreference = firebaseFirestore.collection(Constants.MENU_COLLECTION).document();
            menuItem.setId(documentreference.getId());
            documentreference.set(menuItem).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(AddMealActivity.this, "succesfully added meal to menu",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }

    private boolean validateData(){
        if (addmeal.getText().length()==0 || description.getText().length()==0 || mealtype.getText().length()==0 || cuisinetype.getText().length()==0){
            Toast.makeText(this, "must enter a meal, a description, a meal type, and a cuisine type",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}