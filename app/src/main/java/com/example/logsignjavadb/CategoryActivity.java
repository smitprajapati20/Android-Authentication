package com.example.logsignjavadb;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class CategoryActivity extends AppCompatActivity {

    int[] idArray = {1,2,3,4,5,6,7,8};
    String[] nameArray = {"Electronics","Books","Cloths","Shoes","Bags","Bike","Car","Mobile"};
    int[] imageArray = {R.drawable.electronics,R.drawable.books,R.drawable.clothes,R.drawable.shoes,R.drawable.bags,R.drawable.bike,R.drawable.car,R.drawable.mobile};

    RecyclerView category_recycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category);

        category_recycler = findViewById(R.id.category_recycler);

        category_recycler.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));

        CategoryAdapter adapter = new CategoryAdapter(CategoryActivity.this, idArray, nameArray, imageArray);
        category_recycler.setAdapter(adapter);
    }
}