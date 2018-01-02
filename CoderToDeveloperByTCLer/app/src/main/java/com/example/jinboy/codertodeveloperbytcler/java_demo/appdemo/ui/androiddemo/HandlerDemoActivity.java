package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.androiddemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.Person;

public class HandlerDemoActivity extends Activity implements View.OnClickListener {
    private TextView idTv;
    private Button id_btn1,id_btn2,id_btn3,id_btn4,id_btn5,id_btn6,id_btn7,id_btn8,id_btn9;
    private ImageView idImg;

    private  int images[]= {R.drawable.android_img,R.drawable.java_img,R.drawable.view_img};
    private int index=0;

    Handler customHander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            idTv.setText("msg.arg1>"+msg.arg1+ "\nmsg.arg2>" +msg.arg2 +"\nmsg.obj>"+((Person)msg.obj).toString());
        }
    };

    Handler interceptHander = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Toast.makeText(HandlerDemoActivity.this, "callback handleMessage", Toast.LENGTH_SHORT).show();
            System.out.println("is intercept Handler>"+msg.what);
            // 设置true拦截消息
            return true;
        }
    }){
        @Override
        public void handleMessage(Message msg) {
            System.out.println("is intercept Handler");
        }
    };

    Handler handler = new Handler();
    Thread myThread = new Thread(){
        @Override
        public void run() {
            index++;
            index = index%3;
            System.out.println(index);
            idImg.setImageResource(images[index]);
            handler.postDelayed(myThread,1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_demo_activity);

        id_btn1 = (Button)findViewById(R.id.id_btn1);
        id_btn2 = (Button)findViewById(R.id.id_btn2);
        id_btn3 = (Button)findViewById(R.id.id_btn3);
        id_btn4 = (Button)findViewById(R.id.id_btn4);
        id_btn5 = (Button)findViewById(R.id.id_btn5);
        id_btn6 = (Button)findViewById(R.id.id_btn6);
        id_btn7 = (Button)findViewById(R.id.id_btn7);
        id_btn8 = (Button)findViewById(R.id.id_btn8);
        id_btn9 = (Button)findViewById(R.id.id_btn9);

        idTv = (TextView) findViewById(R.id.id_tv);
        idImg = (ImageView)findViewById(R.id.id_img) ;

        id_btn1.setOnClickListener(this);
        id_btn2.setOnClickListener(this);
        id_btn3.setOnClickListener(this);
        id_btn4.setOnClickListener(this);
        id_btn5.setOnClickListener(this);
        id_btn6.setOnClickListener(this);
        id_btn7.setOnClickListener(this);
        id_btn8.setOnClickListener(this);
        id_btn9.setOnClickListener(this);

        new Thread() {
            @Override
            public void run() {
                idTv.setText("看我");
            }
        }.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_btn1:
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 3);
                            idTv.setText("蹦啦");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
            case R.id.id_btn2:
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 3);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    idTv.setText("又长帅了");
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
            case R.id.id_btn3:
                handler.postDelayed(myThread,1000);
                break;
            case R.id.id_btn4:
                handler.removeCallbacks(myThread);
                break;
            case R.id.id_btn5:
                Person dog=new Person("金三胖",1);
//                Message message = new Message();
                Message message= customHander.obtainMessage();
                message.arg1 = 1;
                message.arg2 = 2;
                message.obj = dog;
//                customHander.sendMessage(message);
                message.sendToTarget();
                break;
            case R.id.id_btn6:
                interceptHander.sendEmptyMessage(1);
                break;

            case R.id.id_btn7:
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000*3);
//                    idTv.setText("UI线程更新UI会出现什么异常呢？");
                            new Handler().post(new Runnable() {
                                @Override
                                public void run() {
                                    idTv.setText("又蹦啦");
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;

            case R.id.id_btn8:
                startActivity(new Intent(this,HandlerActivity.class));
                break;
            case R.id.id_btn9:
                startActivity(new Intent(this,HandlerThreadActivity.class));
                break;
        }
    }

}
