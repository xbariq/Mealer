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

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.ViewHolder>{
    List<MenuItem> menuItemList;


    public MenuListAdapter(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new ViewHolder(view);

    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(menuItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return menuItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindData (MenuItem menuItem){
            TextView itemname= itemView.findViewById(R.id.itemname);
            TextView description= itemView.findViewById(R.id.description);
            ImageView delete= itemView.findViewById (R.id.deleteitem);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseFirestore firebaseFirestore= FirebaseFirestore.getInstance();
                    firebaseFirestore.collection(Constants.MENU_COLLECTION).document(menuItem.getId()).delete()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        menuItemList.remove(menuItem);
                                        notifyDataSetChanged();
                                    }
                                }
                            });
                }
            });
            itemname.setText(menuItem.getMeal());
            description.setText(menuItem.getDescription());
        }
    }
}
