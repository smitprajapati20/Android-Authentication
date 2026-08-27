package com.example.logsignjavadb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    ImageView profile_m;
    Button logout_m, delete_m,subCategory_m,category_m;
    SQLiteDatabase db;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase(ConstatSP.DataB,MODE_PRIVATE,null);
        sp = getSharedPreferences(ConstatSP.DataB, MODE_PRIVATE);

        String userTable = "CREATE TABLE IF NOT EXISTS user(userId INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(30), email VARCHAR(30), contact VARCHAR(10), password VARCHAR(30))";
        db.execSQL(userTable);

        logout_m = findViewById(R.id.logout);
        delete_m = findViewById(R.id.delete_btn);
        profile_m = findViewById(R.id.profile_btn);
        category_m = findViewById(R.id.category);
        subCategory_m = findViewById(R.id.subCategory);

        String email = sp.getString(ConstatSP.email,null);

        logout_m.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, SignUp.class));
            sp.edit().clear().commit();
            Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show();
        });

        delete_m.setOnClickListener(view -> {
            Snackbar.make(view, "Are you sure you want to delete your profile?", Snackbar.LENGTH_LONG)
                    .setAction("DELETE", view1 -> {
                        String delete = "DELETE FROM user WHERE email= '"+email+"'";
                        db.execSQL(delete);
                        startActivity(new Intent(MainActivity.this, Login.class));
                        sp.edit().clear().commit();
                        Toast.makeText(this, "Profile Deleted Successfully", Toast.LENGTH_SHORT).show();
                    })
                    .show();
        });

        profile_m.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, Profile.class));
        });

        subCategory_m.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, SubCategoryActivity.class));
        });

        category_m.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, CategoryActivity.class));
        });
    }
}