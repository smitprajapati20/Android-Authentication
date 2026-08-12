package com.example.logsignjavadb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

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

        db = openOrCreateDatabase(ConstatSP.DataB,MODE_PRIVATE,null);
        String userTable = "CREATE TABLE IF NOT EXISTS user(userid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR (30), email VARCHAR (30), contact VARCHAR (10), password VARCHAR (30))";
        db.execSQL(userTable);

        sp = getSharedPreferences(ConstatSP.DataB, MODE_PRIVATE);

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
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                email_l.setError("Incorrect format");
                email_l.requestFocus();
            }
            else if (pass.isEmpty()){
                pass_l.setError("Pass is required");
                pass_l.requestFocus();
            }
            else if (pass.length() < 5){
                pass_l.setError("Password Length is lower than expected");
                pass_l.requestFocus();
            }
            else {
                Cursor cursor = db.rawQuery("SELECT * FROM user WHERE email = ? AND password = ?", new String[]{email, pass});
                if(cursor.moveToFirst()){
                        sp.edit()
                                .putInt(ConstatSP.userid, cursor.getInt(0))
                                .putString(ConstatSP.name, cursor.getString(1))
                                .putString(ConstatSP.email, cursor.getString(2))
                                .putString(ConstatSP.contact, cursor.getString(3))
                                .putString(ConstatSP.password, cursor.getString(4))
                                .apply();
                        startActivity(new Intent(Login.this, MainActivity.class));
                    Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Login.this,"Login Failed",Toast.LENGTH_SHORT).show();
                }
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