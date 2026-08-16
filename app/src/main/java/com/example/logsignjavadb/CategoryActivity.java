package com.example.logsignjavadb;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class CategoryActivity extends AppCompatActivity {

    int[] idArray = {1,2,3,4,5};
    String[] nameArray = {"Electronics","Books","Cloths","Shoes","Bags"};
    int[] imageArray = {R.drawable.electronics,R.drawable.books,R.drawable.clothes,R.drawable.shoes,R.drawable.bags};

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category);

        recyclerView = findViewById(R.id.category_recycler);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL));

        CategoryAdapter adapter = new CategoryAdapter(CategoryActivity.this,idArray,nameArray,imageArray);
        recyclerView.setAdapter(adapter);
    }
}