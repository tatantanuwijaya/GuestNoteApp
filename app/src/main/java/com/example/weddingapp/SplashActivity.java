package com.example.weddingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread=new Thread(){
            public void run(){
                try {
                    sleep(4000);
                }catch (InterruptedException ie){
                    ie.printStackTrace();
                }finally {
                    startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
                    finish();
                }
            }
        };
        thread.start();
    }
}