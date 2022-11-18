package com.example.mealerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ComplaintListAdapter extends RecyclerView.Adapter<ComplaintListAdapter.ViewHolder>{

    List<ComplaintData> complaintList;
    public ComplaintListAdapter(List<ComplaintData> complaintList){
        this.complaintList = complaintList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_complaint, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(complaintList.get(position));
    }

    @Override
    public int getItemCount() {
        return complaintList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        TextView tvComplaint = itemView.findViewById(R.id.tvComplaint);
        TextView tvCookName = itemView.findViewById(R.id.tvCookName);
        ImageView imgApprove = itemView.findViewById(R.id.imgApprove);
        ImageView imgReject = itemView.findViewById(R.id.imgReject);

        void bindData(ComplaintData complaintData) {
            tvComplaint.setText(complaintData.getComplaint());
            tvCookName.setText(complaintData.getCookName());

            imgApprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String userId = complaintData.getCookId();
                    FirebaseFirestore firebaseFirestore= FirebaseFirestore.getInstance();
                    firebaseFirestore.collection(Constants.USER_COLLECTION).document(userId)
                            .update(Constants.IS_ACTIVE_KEY, false)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        firebaseFirestore.collection(Constants.COMPLAINTS_COLLECTION)
                                                .document(complaintData.getComplaintId()).delete()
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()){
                                                            complaintList.remove(complaintData);
                                                            notifyDataSetChanged();
                                                        }
                                                    }
                                                });
                                    }
                                }
                            });



                }
            });

            imgReject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseFirestore firebaseFirestore= FirebaseFirestore.getInstance();
                    firebaseFirestore.collection(Constants.COMPLAINTS_COLLECTION)
                            .document(complaintData.getComplaintId()).delete()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        complaintList.remove(complaintData);
                                        notifyDataSetChanged();
                                    }
                                }
                            });
                }
            });
        }


    }
}
