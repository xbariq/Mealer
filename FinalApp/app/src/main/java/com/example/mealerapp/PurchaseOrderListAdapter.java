package com.example.mealerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class PurchaseOrderListAdapter extends RecyclerView.Adapter<PurchaseOrderListAdapter.ViewHolder> {

    List<PurchaseRequestData> purchaseOrderList;

    public PurchaseOrderListAdapter(List<PurchaseRequestData> purchaseOrderList) {
        this.purchaseOrderList = purchaseOrderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_purchase_request, parent, false);
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
            TextView clientName= itemView.findViewById(R.id.tvClientName);
            ImageView imgApprove = itemView.findViewById(R.id.imgApprove);
            ImageView imgReject = itemView.findViewById(R.id.imgReject);

            meal.setText(purchaseRequestData.getMenuItem().getMeal());
            clientName.setText(purchaseRequestData.getClientName());

            imgReject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                    firebaseFirestore.collection(Constants.PURCHASE_ORDER_COLLECTION).document(purchaseRequestData.getId())
                            .update("status", Constants.DECLINED);
                    purchaseOrderList.remove(purchaseRequestData);
                    notifyDataSetChanged();
                }
            });

            imgApprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseFirestore firebaseFirestore= FirebaseFirestore.getInstance();
                    firebaseFirestore.collection(Constants.PURCHASE_ORDER_COLLECTION).document(purchaseRequestData.getId())
                            .update ("status", Constants.ACCEPTED);
                    purchaseOrderList.remove(purchaseRequestData);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
