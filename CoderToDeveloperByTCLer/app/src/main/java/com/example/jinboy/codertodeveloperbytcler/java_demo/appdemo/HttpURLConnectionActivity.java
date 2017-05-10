package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.getdata.GetData;


/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/05/09
 *     desc   : 演示HttpURLConnect Get请求获取网页，图片，图片代码
 *     version: 1.0
 * </pre>
 */
public class HttpURLConnectionActivity extends Activity implements View.OnClickListener {
    private Button bt_tobaidu, bt_getimage, bt_tobaiduhtml;
    private WebView wb_tobaidu;
    private TextView tv_tobaiduhtml;
    private ImageView iv_getimage;
    private Bitmap bitmap;
    private String detail = "";
    private final static String PIC_URL = "http://d.hiphotos.baidu.com/image/pic/item/b03533fa828ba61e0bd9f7ef4534970a304e593e.jpg";
    private final static String HTML_URL = "https://www.baidu.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpurlconnectionactivity);
        bt_tobaidu = (Button) findViewById(R.id.bt_tobaidu);
        bt_getimage = (Button) findViewById(R.id.bt_getimage);
        bt_tobaiduhtml = (Button) findViewById(R.id.bt_tobaiduhtml);
        wb_tobaidu = (WebView) findViewById(R.id.wb_tobaidu);
        tv_tobaiduhtml = (TextView) findViewById(R.id.tv_tobaiduhtml);
        iv_getimage = (ImageView) findViewById(R.id.iv_getimage);
        bt_tobaidu.setOnClickListener(this);
        bt_getimage.setOnClickListener(this);
        bt_tobaiduhtml.setOnClickListener(this);
    }

    // 用于刷新界面
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0x001:
                    wb_tobaidu.setVisibility(View.GONE);
                    tv_tobaiduhtml.setVisibility(View.GONE);
                    iv_getimage.setVisibility(View.VISIBLE);
                    iv_getimage.setImageBitmap(bitmap);
                    Toast.makeText(HttpURLConnectionActivity.this, "图片加载完毕", Toast.LENGTH_SHORT).show();
                    break;
                case 0x002:
                    wb_tobaidu.setVisibility(View.GONE);
                    iv_getimage.setVisibility(View.GONE);
                    tv_tobaiduhtml.setVisibility(View.VISIBLE);
                    tv_tobaiduhtml.setText(detail);
                    Toast.makeText(HttpURLConnectionActivity.this, "HTML代码加载完毕", Toast.LENGTH_SHORT).show();
                    break;
                case 0x003:
                    tv_tobaiduhtml.setVisibility(View.GONE);
                    iv_getimage.setVisibility(View.GONE);
                    wb_tobaidu.setVisibility(View.VISIBLE);
                    wb_tobaidu.loadDataWithBaseURL("", detail, "text/html", "UTF-8", "");
                    Toast.makeText(HttpURLConnectionActivity.this, "网页加载完毕", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }

        ;
    };

    public void tobaidu() {
        new Thread() {
            public void run() {
                try {
                    detail = GetData.getHtml(HTML_URL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0x003);
            }
        }.start();
    }

    public void getimage() {
        new Thread() {
            public void run() {
                try {
                    byte[] data = GetData.getImage(PIC_URL);
                    bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0x001);
            }
        }.start();
    }

    public void tobaiduhtml() {
        if ("".equals(detail)) {
            Toast.makeText(HttpURLConnectionActivity.this, "先请求网页~", Toast.LENGTH_SHORT).show();
        } else {
            handler.sendEmptyMessage(0x002);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_tobaidu:
                tobaidu();
                break;
            case R.id.bt_getimage:
                getimage();
                break;
            case R.id.bt_tobaiduhtml:
                tobaiduhtml();
                break;
        }
    }
}
