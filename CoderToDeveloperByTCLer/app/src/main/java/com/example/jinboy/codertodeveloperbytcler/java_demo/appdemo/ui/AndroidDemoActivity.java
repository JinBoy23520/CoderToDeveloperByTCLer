package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.androiddemo.FileOperationsActivity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.androiddemo.HandlerDemoActivity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.androiddemo.SDcardOperationsActivity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.androiddemo.SharedPreferenceActivity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.view.TitleView;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.util.StatusBarUtil;

public class AndroidDemoActivity extends Activity implements View.OnClickListener {
    private Button button1,button2,button3,button4;
    private TitleView title_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_demo_activity);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setLeftToBack(this);
        StatusBarUtil.setColor(this,0xffffff,0);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Intent intent = new Intent(this,FileOperationsActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent1 = new Intent(this,SDcardOperationsActivity.class);
                startActivity(intent1);
                break;
            case R.id.button3:
                Intent intent2 = new Intent(this,SharedPreferenceActivity.class);
                startActivity(intent2);
                break;
            case R.id.button4:
                Intent intent3 = new Intent(this,HandlerDemoActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
