package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;

public class MyViewActivity extends Activity implements View.OnClickListener{
    private Button myButton1,myButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_activity);
        myButton1 = (Button) findViewById(R.id.my_button1);
        myButton1.setOnClickListener(this);
        myButton2 = (Button) findViewById(R.id.my_button2);
        myButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_button1:
                startActivity(new Intent(this,MapViewActivity.class));
                break;
            case R.id.my_button2:
                startActivity(new Intent(this,MapViewActivity.class));
                break;
            default:
        }
    }
}
