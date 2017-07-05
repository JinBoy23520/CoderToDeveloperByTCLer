package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.androiddemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;

public class HandlerActivity extends AppCompatActivity {
    private MyThread myThread;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            System.out.println("UI ==:"+ Thread.currentThread());
        }
    };

    class MyThread extends Thread {
        public Handler handler;
        @Override
        public void run() {
            Looper.prepare();
            handler= new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    System.out.println("currentThread:=="+ Thread.currentThread());
                }
            };
            Looper.loop();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        myThread = new MyThread();
        myThread.start();
        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread.handler.sendEmptyMessage(1);
        handler.sendEmptyMessage(1);
    }
}
