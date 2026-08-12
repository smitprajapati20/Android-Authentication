package com.example.logsignjavadb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

//    ImageView splash_Image;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);

        sp = getSharedPreferences(ConstatSP.DataB, MODE_PRIVATE);
//        splash_Image = findViewById(R.id.splash_image);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sp.getString(ConstatSP.email, "").equals("")){
                    startActivity(new Intent(SplashScreen.this,Login.class));
                }
                else {
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                }
                finish();
            }
        },2000);
    }
}