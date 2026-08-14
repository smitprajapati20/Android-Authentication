package com.example.logsignjavadb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Profile extends AppCompatActivity {

    EditText name_P, email_P, contact_P, password_P, confirm_password_P;
    Button edit_p, update_p;
    ImageView back;
    SharedPreferences sp;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        db = openOrCreateDatabase("FirstJavaDB",MODE_PRIVATE,null);
        sp = getSharedPreferences("FirstJavaDB", MODE_PRIVATE);

        String userTable = "CREATE TABLE IF NOT EXISTS user(userid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR (30), email VARCHAR (30), contact VARCHAR (10), password VARCHAR (30))";
        db.execSQL(userTable);

        name_P = findViewById(R.id.name_profile);
        email_P = findViewById(R.id.email_profile);
        contact_P = findViewById(R.id.contact_profile);
        password_P = findViewById(R.id.password_profile);
        confirm_password_P = findViewById(R.id.confirm_password_profile);
        edit_p = findViewById(R.id.profile_btn);
        update_p = findViewById(R.id.update_profile_btn);
        back = findViewById(R.id.back);

        name_P.setText(sp.getString("NAME",null));
        email_P.setText(sp.getString("EMAIL",null));
        contact_P.setText(sp.getString("CONTACT",null));
        password_P.setText(sp.getString("PASSWORD",null));

        setData(false);

        back.setOnClickListener(view -> {
            startActivity(new Intent(Profile.this, MainActivity.class));
        });

        edit_p.setOnClickListener(view -> {
            setData(true);
            confirm_password_P.setVisibility(View.VISIBLE);
            update_p.setVisibility(View.VISIBLE);
            edit_p.setVisibility(View.GONE);
        });

        update_p.setOnClickListener(view ->  {
                String updateUser = "UPDATE user SET name = '" + name_P.getText().toString() + "', " +
                        "email = '" + email_P.getText().toString() + "'," +
                        " contact = '" + contact_P.getText().toString() + "', " +
                        "password = '" + password_P.getText().toString() + "' " +
                        "WHERE userid = '" + sp.getInt(ConstatSP.userid, 0) + "'";
                db.execSQL(updateUser);

                sp.edit().putString(ConstatSP.name, name_P.getText().toString()).commit();
                sp.edit().putString(ConstatSP.email, email_P.getText().toString()).commit();
                sp.edit().putString(ConstatSP.contact, contact_P.getText().toString()).commit();
                sp.edit().putString(ConstatSP.password, password_P.getText().toString()).commit();

                Toast.makeText(Profile.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();

                edit_p.setVisibility(View.VISIBLE);
                confirm_password_P.setVisibility(View.GONE);
                update_p.setVisibility(View.GONE);
                setData(false);
            });
        }

    private void setData(boolean b) {
        name_P.setEnabled(b);
        email_P.setEnabled(b);
        contact_P.setEnabled(b);
        password_P.setEnabled(b);
    }

    }
