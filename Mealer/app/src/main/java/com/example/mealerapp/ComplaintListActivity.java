package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class ComplaintListActivity extends AppCompatActivity {

    RecyclerView rvComplaints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_list);
        rvComplaints = findViewById(R.id.rvComplaints);

        getData();
    }

    private void getData() {

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection(Constants.COMPLAINTS_COLLECTION).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            List<ComplaintData> complaintList = task.getResult().toObjects(ComplaintData.class);
                            ComplaintListAdapter complaintListAdapter = new ComplaintListAdapter(complaintList);
                            rvComplaints.setLayoutManager(new LinearLayoutManager(ComplaintListActivity.this));
                            rvComplaints.setAdapter(complaintListAdapter);
                        } else {
                            Toast.makeText(ComplaintListActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}