package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.recyclerview.SwipeRefreshRecyclerViewActivity;

public class AndroidUIActivity extends Activity implements View.OnClickListener{

    private Button ui_button1,uiButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_activity);
        ui_button1 = (Button) findViewById(R.id.ui_button1);
        uiButton2 = (Button) findViewById(R.id.ui_button2);
        ui_button1.setOnClickListener(this);
        uiButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ui_button1:
                Intent intent = new Intent(this,SwipeRefreshRecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.ui_button2:
                Intent intent2 = new Intent(this,TimeViewActivity.class);
                startActivity(intent2);
                break;
            default:
        }
    }
}
