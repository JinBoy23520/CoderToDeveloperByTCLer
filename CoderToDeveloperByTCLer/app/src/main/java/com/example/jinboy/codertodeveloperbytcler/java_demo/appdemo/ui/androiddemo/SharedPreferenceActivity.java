package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.androiddemo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.util.SPUtils;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.util.SharedHelper;

import java.util.Map;

public class SharedPreferenceActivity extends Activity {
    private EditText editname;
    private EditText editpasswd;
    private Button btnlogin,btnshow,button_clear;
    private String strname;
    private String strpasswd;
    private SharedHelper sh;
    private Context mContext;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_preference_activity);
        mContext = getApplicationContext();
        sh = new SharedHelper(mContext);
        intViews();
    }

    private void intViews() {
        editname = (EditText)findViewById(R.id.editname);
        editpasswd = (EditText)findViewById(R.id.editpasswd);
        button_clear = (Button)findViewById(R.id.button_clear);
        btnshow = (Button)findViewById(R.id.buttonshow);
        btnlogin = (Button)findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strname = editname.getText().toString();
                strpasswd = editpasswd.getText().toString();
                sh.save(strname,strpasswd);
            }
        });

        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获得第一个应用的包名,从而获得对应的Context,需要对异常进行捕获
                try {
                    mContext = createPackageContext("com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.androiddemo"
                            , Context.CONTEXT_IGNORE_SECURITY);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                //根据Context取得对应的SharedPreferences
                sp = mContext.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
                String name = sp.getString("username", "");
                String passwd = sp.getString("passwd", "");
                Toast.makeText(getApplicationContext(), "Demo1的SharedPreference存的\n用户名为：" +
                        name + "\n密码为：" + passwd, Toast.LENGTH_SHORT).show();
            }
        });

        button_clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SPUtils.clear(mContext);
                Toast.makeText(getApplicationContext(), "已删除保存信息" , Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Map<String,String> data = sh.read();
        editname.setText(data.get("username"));
        editpasswd.setText(data.get("passwd"));
    }
}
