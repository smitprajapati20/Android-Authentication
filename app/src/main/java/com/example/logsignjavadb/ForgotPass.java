package com.example.logsignjavadb;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class ForgotPass extends AppCompatActivity {

    EditText newPass_f, c_newPass_f, email_f;
    Button change_pass;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot_pass);

        db = openOrCreateDatabase("FirstJavaDB",MODE_PRIVATE,null);
        String userTable = "CREATE TABLE IF NOT EXISTS user(userid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR (30), email VARCHAR (30), contact VARCHAR (10), password VARCHAR (30))";
        db.execSQL(userTable);

        email_f = findViewById(R.id.email_forget);
        newPass_f = findViewById(R.id.newPass_forget);
        c_newPass_f = findViewById(R.id.cNewPass_forget);
        change_pass = findViewById(R.id.forgetPassword_btn);

        change_pass.setOnClickListener(view -> {
            String email = email_f.getText().toString().trim();
            String newPass = newPass_f.getText().toString().trim();
            String confirmPass = c_newPass_f.getText().toString().trim();

            if (email.isEmpty()){
                email_f.setError("Email is required");
                email_f.requestFocus();

            }
            else if (newPass.isEmpty()){
                newPass_f.setError("Pass is required");
                newPass_f.requestFocus();

            }
            else if (newPass.length() < 5) {
                newPass_f.setError("Password Length is lower than expected");
                newPass_f.requestFocus();

            }
            else if (confirmPass.length() < 5) {
                c_newPass_f.setError("Password Length is lower than expected");
                c_newPass_f.requestFocus();

            }
            else if (!confirmPass.equals(newPass)){
                c_newPass_f.setError("Password does not match");
                c_newPass_f.requestFocus();

            }
            else {
                String change = "UPDATE user SET password = '"+newPass+"' WHERE email = '"+email+"'";
                db.execSQL(change);
                Toast.makeText(ForgotPass.this,"Pass Changed Successfully",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ForgotPass.this,Login.class));
            }
        });
    }
}