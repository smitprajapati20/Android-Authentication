package com.example.logsignjavadb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText name_M;
    Button logout_m, delete_m,profile_m,products_m;
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

        name_M = findViewById(R.id.main_name);
        logout_m = findViewById(R.id.logout);
        delete_m = findViewById(R.id.delete_btn);
        profile_m = findViewById(R.id.profile);
        products_m = findViewById(R.id.products);

        String email = sp.getString(ConstatSP.email,null);

        name_M.setText(sp.getString(ConstatSP.name, null));

        logout_m.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, SignUp.class));
            sp.edit().clear().commit();
            Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show();
        });

        delete_m.setOnClickListener(view -> {
            String delete = "DELETE FROM user WHERE email= '"+email+"'";
            db.execSQL(delete);
            startActivity(new Intent(MainActivity.this, Login.class));
            sp.edit().clear().commit();
            Toast.makeText(this, "Profile Deleted Successfully", Toast.LENGTH_SHORT).show();
        });

        profile_m.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, Profile.class));
        });

        products_m.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, Products.class));
        });
    }
}