package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class ClientPurchaseRequestActivity extends AppCompatActivity {

    RecyclerView rvPurchaseRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_request_list);
        rvPurchaseRequests= findViewById(R.id.rvPurchaseRequests);
        getData();
    }

    private void getData(){
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection(Constants.PURCHASE_ORDER_COLLECTION).whereEqualTo("clientId", FirebaseAuth.getInstance().getCurrentUser().getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            List<PurchaseRequestData> purchaseRequestData = task.getResult().toObjects(PurchaseRequestData.class);
                            ClientPurchaseRequestAdapter orderListAdapter= new ClientPurchaseRequestAdapter(purchaseRequestData);
                            rvPurchaseRequests.setAdapter(orderListAdapter);
                            rvPurchaseRequests.setLayoutManager(new LinearLayoutManager(ClientPurchaseRequestActivity.this));
                        }
                    }
                });
    }

}