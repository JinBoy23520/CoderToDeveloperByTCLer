package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.androidui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.util.FileHelper;

import java.io.IOException;

public class UIActivity extends Activity implements View.OnClickListener{

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_activity);
        button1 = (Button) findViewById(R.id.button1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:

                break;

        }
    }
}
