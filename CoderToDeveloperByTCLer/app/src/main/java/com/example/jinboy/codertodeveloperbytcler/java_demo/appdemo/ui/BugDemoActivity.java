package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2018/05/18
 *     desc   : 用于积累一些兼容性bug
 *     version: 1.0
 * </pre>
 */

public class BugDemoActivity extends Activity implements View.OnClickListener {

    private EditText etxt_code;
    private Button btn_submit;

    private TextView txtSendCode;
    private Chronometer timer;
    // 倒计时
    private static final int COUNT_MAX = 120;
    private int countdownNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bug_layout_activity);
        int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
        verifyStoragePermissions(this);
        TextView txt_phone = (TextView) findViewById(R.id.txt_phone);
        String phone = getIntent().getStringExtra("regist_phone");
        if (!TextUtils.isEmpty(phone)) {
            txt_phone.setText(String.format("%s %s", txt_phone.getText().toString().trim(), phone));
        }

        etxt_code = (EditText) findViewById(R.id.etxt_code);
        etxt_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etxt_code.getText().toString())) {
                    btn_submit.setEnabled(true);
                    //btn_submit.setTextColor(getResources().getColor(R.color.txt_black));
                } else {
                    btn_submit.setEnabled(false);
                    //btn_submit.setTextColor(getResources().getColor(R.color.txt_hint));
                }
            }
        });
        etxt_code.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && event.getAction() == KeyEvent.ACTION_DOWN) {
//                    doRegist();
                    return true;
                }
                return false;
            }
        });
        txtSendCode = (TextView) findViewById(R.id.txt_send_msg);
        txtSendCode.setOnClickListener(this);
        timer = (Chronometer) findViewById(R.id.chronometer);
        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer arg0) {
                if (countdownNum <= 0) {
//                    resetSendBtn();
                    return;
                }
                txtSendCode.setText(String.format(getResources().getString(R.string.time_left), "" + countdownNum));
                countdownNum--;
            }
        });
        doCountdown();

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        btn_submit.setEnabled(false);
        //btn_submit.setTextColor(getResources().getColor(R.color.txt_hint));
    }

    /**
     * 倒计时
     */
    private void doCountdown() {
        countdownNum = COUNT_MAX;
        txtSendCode.setEnabled(false);
        txtSendCode.setFocusable(false);
        timer.start();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_send_msg:

            default:
                break;
        }
    }

    private boolean verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return permission == PackageManager.PERMISSION_GRANTED;
    }

}




