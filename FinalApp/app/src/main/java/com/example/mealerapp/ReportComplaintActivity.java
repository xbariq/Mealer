package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ReportComplaintActivity extends AppCompatActivity {

    PurchaseRequestData purchaseRequestData;
    EditText complaint;
    Button submitComplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_complaint);
        purchaseRequestData = (PurchaseRequestData) getIntent().getSerializableExtra(Constants.PURCHASE_REQUEST_DATA);
        complaint = findViewById(R.id.complaint);
        submitComplaint = findViewById(R.id.btnSubmitComplaint);
        submitComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createComplaint();
            }
        });

    }

    private void createComplaint() {
        ComplaintData complaintData = new ComplaintData(purchaseRequestData.getClientId(), purchaseRequestData.getClientName(), purchaseRequestData.getCookId(), purchaseRequestData.getCookName(), complaint.getText().toString());
        FirebaseFirestore firebaseFirestore= FirebaseFirestore.getInstance();
        DocumentReference document= firebaseFirestore.collection(Constants.COMPLAINTS_COLLECTION).document();
        complaintData.setComplaintId(document.getId());
        document.set(complaintData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ReportComplaintActivity.this, "complaint was submitted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
