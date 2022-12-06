package com.example.mealerapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ClientPurchaseRequestAdapter extends RecyclerView.Adapter<ClientPurchaseRequestAdapter.ViewHolder>{
    List<PurchaseRequestData> purchaseOrderList;

    public ClientPurchaseRequestAdapter(List<PurchaseRequestData> purchaseOrderList) {
        this.purchaseOrderList = purchaseOrderList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_client_purchase_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(purchaseOrderList.get(position));
    }

    @Override
    public int getItemCount() {
        return purchaseOrderList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindData(PurchaseRequestData purchaseRequestData) {
            TextView meal= itemView.findViewById(R.id.tvMeal);
            TextView cookName= itemView.findViewById(R.id.tvCookName);
            TextView status= itemView.findViewById(R.id.tvStatus);
            Button report= itemView.findViewById(R.id.btnReport);


            meal.setText(purchaseRequestData.getMenuItem().getMeal());
            cookName.setText(purchaseRequestData.getCookName());
            status.setText(purchaseRequestData.getStatus());

            if (purchaseRequestData.getStatus().equals(Constants.ACCEPTED)){
                report.setVisibility(View.VISIBLE);
            }
            else {
                report.setVisibility(View.GONE);
            }
            report.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(view.getContext(), ReportComplaintActivity.class);
                    intent.putExtra(Constants.PURCHASE_REQUEST_DATA,purchaseRequestData);
                    view.getContext().startActivity(intent);

                }
            });

        }
    }
}
