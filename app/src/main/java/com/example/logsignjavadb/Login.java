package com.example.logsignjavadb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class Login extends AppCompatActivity {

    EditText email_l, pass_l;
    TextView  sug_signup_l, f_pass_l;
    Button login_l;
    SQLiteDatabase db;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        db = openOrCreateDatabase("FirstJavaDB",MODE_PRIVATE,null);
        String userTable = "CREATE TABLE IF NOT EXISTS user(userid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR (30), email VARCHAR (30), contact VARCHAR (10), password VARCHAR (30))";
        db.execSQL(userTable);

        sp = getSharedPreferences("FirstJavaDB", MODE_PRIVATE);

        email_l = findViewById(R.id.logMail);
        pass_l = findViewById(R.id.logPass);
        login_l = findViewById(R.id.Login);
        sug_signup_l = findViewById(R.id.SugSignUp);
        f_pass_l = findViewById(R.id.fgpass);

        login_l.setOnClickListener(view -> {
            String email = email_l.getText().toString().trim();
            String pass = pass_l.getText().toString().trim();

            if (email.isEmpty()){
                email_l.setError("Email is required");
                email_l.requestFocus();
                return;
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                email_l.setError("Incorrect format");
                email_l.requestFocus();
            }
            else if (pass.isEmpty()){
                pass_l.setError("Pass is required");
                pass_l.requestFocus();
                return;
            }
            else if (pass.length() < 5){
                pass_l.setError("Password Length is lower than expected");
                pass_l.requestFocus();
                return;
            }
            else {
                String Check = "SELECT * FROM user WHERE email = '"+email+"' AND password = '"+pass+"' ";

                Cursor cursor = db.rawQuery(Check, null);
                if(cursor.getCount() > 0){
                    startActivity(new Intent(Login.this, MainActivity.class));
                    sp.edit().putString("email", email).commit();
                    Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Login.this,"Login Failed",Toast.LENGTH_SHORT).show();
                }
                return;
            }
    });

        f_pass_l.setOnClickListener(view -> {
            startActivity(new Intent(Login.this,ForgotPass.class));
        });

        sug_signup_l.setOnClickListener(view -> {
            startActivity(new Intent(Login.this,SignUp.class));
        });
}
}