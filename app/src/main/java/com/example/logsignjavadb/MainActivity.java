package com.example.logsignjavadb;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText name_M;
    Button logout_m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        name_M = findViewById(R.id.main_name);
        logout_m = findViewById(R.id.logout);

        String name = getIntent().getStringExtra("name");
        name_M.setText(name);

        logout_m.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, SignUp.class));
        });





    }
}