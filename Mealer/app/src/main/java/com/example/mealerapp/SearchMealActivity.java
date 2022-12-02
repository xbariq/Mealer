package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.SearchView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SearchMealActivity extends AppCompatActivity {
    RecyclerView rv;
    SearchView searchView;
    SearchMealAdapter adapter;
    private List<MenuItem> menuList= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_meal);
        rv= findViewById(R.id.rvMenu);
        searchView= findViewById(R.id.search);
        getData();
        initSearchView();
    }

    private void initSearchView(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                performSearch(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                performSearch(s);
                return true;
            }
        });
    }
    @SuppressLint("NewApi")
    private void performSearch(String query){
        List<MenuItem> searchMenu = menuList.stream().filter(new Predicate<MenuItem>() {
            @Override
            public boolean test(MenuItem menuItem) {
                return menuItem.getMeal().toLowerCase().startsWith(query.toLowerCase()) ||
                        menuItem.getMealType().toLowerCase().startsWith(query.toLowerCase()) ||
                        menuItem.getCuisineType().toLowerCase().startsWith(query.toLowerCase());
            }
        }).collect(Collectors.toList());
        adapter.updateList(searchMenu);
    }

    private void getData (){

        FirebaseFirestore firebaseFirestore= FirebaseFirestore.getInstance();

        firebaseFirestore.collection(Constants.USER_COLLECTION).whereEqualTo("role", Constants.COOK_ROLE)
                        .whereEqualTo("active", true).get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                               if (task.isSuccessful()) {
                                   List<Account> users= task.getResult().toObjects(Account.class);
                                   ArrayList<String> userIds =new ArrayList();
                                   for (Account user: users){
                                       userIds.add(user.getUid());
                                   }
                                   getMenu(userIds);
                               }
                            }
                        });

    }

    private void getMenu(ArrayList<String> userIds){
        FirebaseFirestore firebaseFirestore= FirebaseFirestore.getInstance();
        firebaseFirestore.collection(Constants.MENU_COLLECTION).whereIn("cookId", userIds).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            menuList= task.getResult().toObjects(MenuItem.class);
                            adapter= new SearchMealAdapter(menuList);
                            rv.setAdapter(adapter);
                            rv.setLayoutManager(new LinearLayoutManager(SearchMealActivity.this));
                        }
                    }
                });
    }
}