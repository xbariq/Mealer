
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class SearchMealAdapter extends RecyclerView.Adapter<SearchMealAdapter.ViewHolder> {
    List<MenuItem> menuItemList;


    public SearchMealAdapter(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_meal, parent, false);
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindData(MenuItem menuItem) {
            TextView itemname = itemView.findViewById(R.id.itemname);
            Button more= itemView.findViewById(R.id.btnmore);


            TextView mealtype = itemView.findViewById(R.id.mealtype);
            TextView cuisinetype = itemView.findViewById(R.id.cusinetype);

            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(view.getContext(),MealDetailActivity.class);
                    intent.putExtra(Constants.MEAL_DATA,menuItem);
                    view.getContext().startActivity(intent);
                }
            });
            itemname.setText(menuItem.getMeal());

            mealtype.setText(menuItem.getMealType());
            cuisinetype.setText(menuItem.getCuisineType());
        }
    }
    public void updateList(List <MenuItem> list){
        menuItemList= list;
        notifyDataSetChanged();
    }

}
