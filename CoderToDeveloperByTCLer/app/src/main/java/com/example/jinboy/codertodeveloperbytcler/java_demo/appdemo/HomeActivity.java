package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
    }

    public void start1(View view){
        Intent intent = new Intent();
        intent.setClass(HomeActivity.this, CollectionEnumgGnericsActivity.class);
        HomeActivity.this.startActivity(intent);
    }

    public void start2(View view){
        Intent intent = new Intent();
        intent.setClass(HomeActivity.this, HttpURLConnectionActivity.class);
        HomeActivity.this.startActivity(intent);
    }

    public void start3(View view){
        Intent intent = new Intent();
        intent.setClass(HomeActivity.this, XMLtoEntityActivity.class);
        HomeActivity.this.startActivity(intent);
    }
}
