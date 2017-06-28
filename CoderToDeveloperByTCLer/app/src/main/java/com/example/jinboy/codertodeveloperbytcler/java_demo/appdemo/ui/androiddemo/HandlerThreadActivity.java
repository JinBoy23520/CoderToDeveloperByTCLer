package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.androiddemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HandlerThreadActivity extends AppCompatActivity implements View.OnClickListener {
    private Button idBtn1,idBtn2;
    private Handler threadhandler;
    private HandlerThread thread;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            System.out.println("UI thread>" + Thread.currentThread());
            // 给主线程发送消息
            Message message = new Message();
            message.what =1;
            threadhandler.sendMessageDelayed(message, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);
        idBtn1 = (Button) findViewById(R.id.id_btn1);
        idBtn2 = (Button) findViewById(R.id.id_btn2);
        idBtn1.setOnClickListener(this);
        idBtn2.setOnClickListener(this);
        thread = new HandlerThread("Handler Thread");
        thread.start();
        threadhandler = new Handler(thread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                // 处理耗时操作
                System.out.println("current thread>" + Thread.currentThread());
                // 给主线程发送消息
                Message message = new Message();
                message.what =1;
                handler.sendMessageDelayed(message, 1000);
            }
        };
//        threadhandler.sendEmptyMessage(1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_btn1:
                handler.sendEmptyMessage(1);
                break;
            case R.id.id_btn2:
                handler.removeMessages(1);
                threadhandler.removeMessages(1);
                break;
        }
    }
}
