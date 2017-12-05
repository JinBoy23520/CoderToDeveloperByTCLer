package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui;

import android.app.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.widget.DateSelectDialog;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.widget.DoubleTimeSelectDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeViewActivity extends Activity implements View.OnClickListener {
    private Button uiButton1, uiButton2;
    private DoubleTimeSelectDialog mDoubleTimeSelectDialog;
    /**
     * 默认的周开始时间，格式如：yyyy-MM-dd
     **/
    public String defaultWeekBegin;
    /**
     * 默认的周结束时间，格式如：yyyy-MM-dd
     */
    public String defaultWeekEnd;

    public String paramsStrTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_view_activity);
        uiButton1 = (Button) findViewById(R.id.ui_button1);
        uiButton2 = (Button) findViewById(R.id.ui_button2);
        uiButton1.setOnClickListener(this);
        uiButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ui_button1:
                showCustomTimePicker();
                break;
            case R.id.ui_button2:
                picTime();
                break;
            default:
        }
    }

    /**
     * 选择日期
     */
    public void picTime() {

        if (!paramsStrTime.contains("-")) {
            Date date = new Date();
            long time = date.getTime();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            paramsStrTime = format.format(time);
        }

        DateSelectDialog dateSelectDialog = new DateSelectDialog(this, paramsStrTime, new DateSelectDialog.OnDateSelectFinished() {
            @Override
            public void onSelectFinished(String year, String month, String day, String hour, String minute) {
                paramsStrTime = year + "-" + month + "-" + day + " " + hour + ":" + minute;
                String selectedTime = year + "年" + month + "月" + day + "日 "
                        + hour + ":" + minute;
                uiButton2.setText(selectedTime);
            }
        }, false, false);
        dateSelectDialog.show();
    }

    public void showCustomTimePicker() {
        String beginDeadTime = "2017-01-01";
        if (mDoubleTimeSelectDialog == null) {
            mDoubleTimeSelectDialog = new DoubleTimeSelectDialog(this, beginDeadTime, defaultWeekBegin, defaultWeekEnd);
            mDoubleTimeSelectDialog.setOnDateSelectFinished(new DoubleTimeSelectDialog.OnDateSelectFinished() {
                @Override
                public void onSelectFinished(String startTime, String endTime) {
                    uiButton1.setText(startTime.replace("-", ".") + "至\n" + endTime.replace("-", "."));

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

