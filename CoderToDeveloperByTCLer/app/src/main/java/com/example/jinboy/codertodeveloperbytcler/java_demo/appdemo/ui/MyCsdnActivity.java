package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.BlogAuthor;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.adapter.PagerAdapter;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.util.JsoupUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;


public class MyCsdnActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    //    private CircleImageView circleImageView;
    private SimpleDraweeView top_image;
    private TextView tv_name, tv_originalArticleNumber, tv_visitNumber, tv_mark, tv_rank;
    private BlogAuthor blogAuthor;
    private String tvName, tvOriginalArticleNumber, tvVisitNumber, tvMark, tvRank;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.my_csdn_activity);
        initView();
    }

    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        top_image = (SimpleDraweeView) findViewById(R.id.top_image);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        ViewPager viewPager = (ViewPager) findViewById(R.id.id_viewpager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        View headerView = navigationView.getHeaderView(0);
        tv_name = (TextView) headerView.findViewById(R.id.tv_name);
        tv_originalArticleNumber = (TextView) headerView.findViewById(R.id.tv_originalArticleNumber);
        tv_visitNumber = (TextView) headerView.findViewById(R.id.tv_visitNumber);
        tv_mark = (TextView) headerView.findViewById(R.id.tv_mark);
        tv_rank = (TextView) headerView.findViewById(R.id.tv_rank);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        top_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        new LoadImageAsync().execute();
        //handler 处理返回的请求结果
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle data = msg.getData();
                String val = data.getString("value");
                tv_name.setText(tvName);
                tv_originalArticleNumber.setText(tvOriginalArticleNumber);
                tv_visitNumber.setText(tvVisitNumber);
                tv_mark.setText(tvMark);
                tv_rank.setText(tvRank);
                Log.i("mylog", "请求结果-->" + val);
            }
        };

        //新线程进行网络请求
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                BlogAuthor blogAuthor = JsoupUtil.getBlogAutoMessage();
                tvName = blogAuthor.getAuthorName() + "\n" + blogAuthor.getCode()
                        + "\n" + blogAuthor.getMyHelloWorld();
                tvOriginalArticleNumber = blogAuthor.getOriginalArticleNumber();
                tvVisitNumber = blogAuthor.getVisitNumber();
                tvMark = blogAuthor.getMark();
                tvRank = blogAuthor.getRank();
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putString("value", "请求结果");
                msg.setData(data);
                handler.sendMessage(msg);
            }
        };
        new Thread(runnable).start();  //启动子线程
    }

    /**
     * 菜单点击响应函数
     *
     * @param item 菜单
     * @return boolean
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.nav_csdn:
                WebViewActivity.startWebViewActivity(this, "http://my.csdn.net/dt235201314");
                break;
            case R.id.nav_jianshu:
                WebViewActivity.startWebViewActivity(this, "http://www.jianshu.com/u/905c7de5ae83");
                break;
            default:
        }
        return false;
    }

    /**
     * 重写返回键响应函数
     */
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 用来加载主界面顶部头像以及侧边栏顶部头像
     */
    private class LoadImageAsync extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            blogAuthor = JsoupUtil.getBlogAutoMessage();
            String avatarUrl = "";
            if (blogAuthor != null) {
                avatarUrl = blogAuthor.getAvatarUrl();
            }
            return avatarUrl;
        }

        @Override
        protected void onPostExecute(String avatarUrl) {
            Uri uri = Uri.parse(avatarUrl);
            SimpleDraweeView top_image = (SimpleDraweeView) findViewById(R.id.top_image);
            SimpleDraweeView nav_head_image = (SimpleDraweeView) findViewById(R.id.nav_head_image);
            top_image.setImageURI(uri);
            nav_head_image.setImageURI(uri);
        }
    }

}
