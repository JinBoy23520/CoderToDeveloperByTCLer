package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui;

import android.app.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.widget.DoubleTimeSelectDialog;

public class TimeViewActivity extends Activity implements View.OnClickListener {
    private Button ui_button1, uiButton2;
    private DoubleTimeSelectDialog mDoubleTimeSelectDialog;
    public String defaultWeekBegin;//默认的周开始时间，格式如：yyyy-MM-dd
    public String defaultWeekEnd;//默认的周结束时间，格式如：yyyy-MM-dd

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_view_activity);
        ui_button1 = (Button) findViewById(R.id.ui_button1);
//        uiButton2 = (Button) findViewById(R.id.ui_button2);
        ui_button1.setOnClickListener(this);
//        uiButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ui_button1:
                showCustomTimePicker();
                break;
            case R.id.ui_button2:
//                Intent intent2 = new Intent(this,SwipeRefreshRecyclerViewActivity.class);
//                startActivity(intent2);
                break;
            default:
        }
    }

    public void showCustomTimePicker() {
        String beginDeadTime = "2017-01-01";
        if (mDoubleTimeSelectDialog == null) {
            mDoubleTimeSelectDialog = new DoubleTimeSelectDialog(this, beginDeadTime, defaultWeekBegin, defaultWeekEnd);
            mDoubleTimeSelectDialog.setOnDateSelectFinished(new DoubleTimeSelectDialog.OnDateSelectFinished() {
                @Override
                public void onSelectFinished(String startTime, String endTime) {
                    ui_button1.setText(startTime.replace("-", ".") + "至\n" + endTime.replace("-", "."));

                }
            });

            mDoubleTimeSelectDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                }
            });
        }
        if (!mDoubleTimeSelectDialog.isShowing()) {
            mDoubleTimeSelectDialog.recoverButtonState();
            mDoubleTimeSelectDialog.show();
        }
    }
}

