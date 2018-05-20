package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.java.CollectionEnumgGnericsActivity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.java.HttpURLConnectionActivity;

public class JavaDemoActivity extends Activity implements View.OnClickListener {
    private Button button1,button2,button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.java_demo_activity);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                startActivity(new Intent(this,CollectionEnumgGnericsActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this,HttpURLConnectionActivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(this,HttpURLConnectionActivity.class));
                break;
            default:
        }
    }
}
