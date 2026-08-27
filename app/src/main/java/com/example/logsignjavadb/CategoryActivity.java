package com.example.logsignjavadb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    int[] idArray = {1,2,3,4,5,6,7,8};
    String[] nameArray = {"Electronics","Books","Cloths","Shoes","Bags","Bike","Car","Mobile"};
    int[] imageArray = {R.drawable.electronics,R.drawable.books,R.drawable.clothes,R.drawable.shoes,R.drawable.bags,R.drawable.bike,R.drawable.car,R.drawable.mobile};

    RecyclerView category_recycler;
    SQLiteDatabase db;
    ArrayList<CategoryList> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category);

        db = openOrCreateDatabase(ConstatSP.DataB,MODE_PRIVATE,null);

        String userTable = "CREATE TABLE IF NOT EXISTS user(userId INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(30), email VARCHAR(30), contact VARCHAR(10), password VARCHAR(30))";
        db.execSQL(userTable);

        String categoryTable = "CREATE TABLE IF NOT EXISTS category(categoryid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR (50), image VARCHAR (100))";
        db.execSQL(categoryTable);

        category_recycler = findViewById(R.id.category_recycler);

        for(int i = 0; i < nameArray.length; i++){
            String checkCategory = "SELECT * FROM category WHERE name = '"+nameArray[i]+"'";
            Cursor cursor = db.rawQuery(checkCategory, null);
            if(cursor.getCount() == 0){
                String insertCategory = "INSERT INTO category VALUES(NULL, '"+nameArray[i]+"', '"+imageArray[i]+"')";
                db.execSQL(insertCategory);
            }

        }
        String fetchCategory = "SELECT * FROM category";
        Cursor categroyCursor = db.rawQuery(fetchCategory, null);

        arrayList = new ArrayList<>();

        if(categroyCursor.getCount()>0){
            while(categroyCursor.moveToNext()){
                CategoryList list = new CategoryList();
                list.setId(categroyCursor.getInt(0));
                list.setName(categroyCursor.getString(1));
                list.setImage(categroyCursor.getInt(2));
                arrayList.add(list);
            }
        }


        category_recycler.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));

//        CategoryAdapter adapter = new CategoryAdapter(CategoryActivity.this, idArray, nameArray, imageArray);
        CategoryAdapter adapter = new CategoryAdapter(CategoryActivity.this, arrayList);
        category_recycler.setAdapter(adapter);
    }
}