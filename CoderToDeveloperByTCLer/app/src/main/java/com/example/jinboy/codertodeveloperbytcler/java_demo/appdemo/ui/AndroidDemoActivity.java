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
    private Button button1,button2,button3,button4,button5,button6;
    private TitleView title_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_demo_activity);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setContainerBackgroundColor(this,R.color.gray2,0xffefefef);
        title_view.setLeftToBack(this);
//        StatusBarUtil.setColor(this,0xffffff,0);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                startActivity(new Intent(this, FileOperationsActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, SDcardOperationsActivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(this, SharedPreferenceActivity.class));
                break;
            case R.id.button4:
                startActivity(new Intent(this, HandlerDemoActivity.class));
                break;
            case R.id.button5:
                startActivity(new Intent(this, StarAnotherAppActivity.class));
                break;
            case R.id.button6:
                Intent intent = new Intent(this, BugDemoActivity.class);
                intent.putExtra("regist_phone", "13517193924");
                intent.putExtra("regist_pwd", "qq905054549");
                startActivity(intent);
                break;
                default:
        }
    }
}
