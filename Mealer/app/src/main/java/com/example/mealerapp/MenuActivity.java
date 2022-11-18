package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MenuActivity extends AppCompatActivity {
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        rv= findViewById(R.id.rvMenuLayout);

        getData();
    }

    private void getData(){
        FirebaseFirestore firebaseFirestore= FirebaseFirestore.getInstance();
        String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();

        firebaseFirestore.collection(Constants.MENU_COLLECTION).whereEqualTo("cookId", userId).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            List<MenuItem> menuItems= task.getResult().toObjects(MenuItem.class);
                            MenuListAdapter adapter= new MenuListAdapter(menuItems);
                            rv.setLayoutManager(new LinearLayoutManager(MenuActivity.this));
                            rv.setAdapter(adapter);
                        }
                    }
                });

    }
}