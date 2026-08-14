package com.example.logsignjavadb;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class SignUp extends AppCompatActivity {
    EditText name_S,email_S,contact_S,password_S,confirm_password_S;
    TextView sug_Login;
    Button signUp_S;

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        db = openOrCreateDatabase(ConstatSP.DataB,MODE_PRIVATE,null);

        String userTable = "CREATE TABLE IF NOT EXISTS user(userId INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(30), email VARCHAR(30), contact VARCHAR(10), password VARCHAR(30))";
        db.execSQL(userTable);

        name_S = findViewById(R.id.SignName);
        email_S = findViewById(R.id.SignMail);
        contact_S = findViewById(R.id.SignContact);
        password_S = findViewById(R.id.SignPass);
        confirm_password_S = findViewById(R.id.SignConfirmPass);
        signUp_S = findViewById(R.id.SignUp);
        sug_Login = findViewById(R.id.SugLogin);

        signUp_S.setOnClickListener(view -> {
            String name = name_S.getText().toString().trim();
            String email = email_S.getText().toString().trim();
            String contact = contact_S.getText().toString().trim();
            String password = password_S.getText().toString().trim();
            String confirm_password = confirm_password_S.getText().toString().trim();

            if (name.isEmpty()){
                name_S.setError("Name is required");
                name_S.requestFocus();
            }
            else if (email.isEmpty()){
                email_S.setError("Email is required");
                email_S.requestFocus();
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                email_S.setError("Incorrect format");
                email_S.requestFocus();
            }
            else if (contact.isEmpty()){
                contact_S.setError("Contact is required");
                contact_S.requestFocus();
            }
            else if (contact.length() != 10){
                contact_S.setError("Contact length is has to be 10");
                contact_S.requestFocus();
            }
            else if (password.isEmpty()){
                password_S.setError("Pass is required");
                password_S.requestFocus();
            }
            else if (password.length() < 5){
                password_S.setError("Password Length is lower than expected");
                password_S.requestFocus();
            }
            else if (!confirm_password.equals(password)){
                confirm_password_S.setError("Password does not match");
                confirm_password_S.requestFocus();
            }
            else {
                // "CREATE TABLE IF NOT EXISTS user(userId INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(30), email VARCHAR(30), contact VARCHAR(10), password VARCHAR(30))";
                String insertUser = "INSERT INTO user VALUES(null,'"+name+"','"+email+"','"+contact+"','"+password+"')";
                db.execSQL(insertUser);
                startActivity(new Intent(SignUp.this, MainActivity.class));
                Toast.makeText(this, "Welcome", Toast.LENGTH_LONG).show();
            }
        });
        sug_Login.setOnClickListener( view -> {
            startActivity(new Intent(SignUp.this, Login.class));
        });
    }
}