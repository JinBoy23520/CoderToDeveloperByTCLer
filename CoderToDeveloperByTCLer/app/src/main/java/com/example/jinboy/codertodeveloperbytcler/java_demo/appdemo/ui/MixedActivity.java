package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;


public class MixedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mixed_activity);
        Button btn = (Button) findViewById(R.id.btn);
        final EditText etText = (EditText) findViewById(R.id.et_text);
        final BridgeWebView bridgeWebView = (BridgeWebView) findViewById(R.id.JsBridgeWebView);
        bridgeWebView.setDefaultHandler(new DefaultHandler());
        bridgeWebView.setWebChromeClient(new WebChromeClient());
        bridgeWebView.loadUrl("file:///android_asset/a.html");

        /**
         * js调用Android
         *
         *  参数一：getUserInfo就是注册供JS调用的方法名，
         *  参数二：data是JS传过来的参数，
         *  参数三：CallBackFunction 函数中需要把JS需要的response返回给JS
         */
        bridgeWebView.registerHandler("submitFromWeb", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("TAG", "js返回：" + data);
                //显示js传递给Android的消息
                Toast.makeText(MixedActivity.this, "js返回:" + data, Toast.LENGTH_LONG).show();
                //Android返回给JS的消息
                function.onCallBack("我是js调用Android返回数据：" + etText.getText().toString());
            }
        });


        /**
         * Android调用js
         *
         * 参数一：js中的方法名称
         * 参数二：Android传递给js数据
         * 参数三：回调接口，data为Android调用js方法的返回数据
         */
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                bridgeWebView.callHandler("functionInJs", "Android调用js的方法", new CallBackFunction() {
                    @Override
                    public void onCallBack(String data) {
                        Log.e("TAG", "onCallBack:" + data);
                        Toast.makeText(MixedActivity.this, data, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
