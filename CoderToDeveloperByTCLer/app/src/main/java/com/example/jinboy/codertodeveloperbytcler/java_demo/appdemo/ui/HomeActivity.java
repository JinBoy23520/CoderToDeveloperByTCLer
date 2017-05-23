package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.adapter.TabFragmentAdapter;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.fragment.HomeFragment;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.fragment.MineFragment;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.view.CustomViewPager;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.view.TabContainerView;

public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    /**
     * tab图标集合
     */
    private final int ICONS_RES[][] = {
            {
                    R.mipmap.home_tab_mine_normal,
                    R.mipmap.home_tab_mine_focus
            },
            {
                    R.mipmap.home_tab_mine_normal,
                    R.mipmap.home_tab_mine_focus
            }

    };

    /** tab 颜色值*/
    private final int[] TAB_COLORS = new int []{
            R.color.black5e,
            R.color.blue_bg};

    private Fragment[] fragments = {
            new HomeFragment(),
            new MineFragment()
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        initViews();
    }

    public void initViews() {
        TabFragmentAdapter mAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragments);
        CustomViewPager mPager = (CustomViewPager) findViewById(R.id.tab_pager);
        //设置当前可见Item左右可见page数，次范围内不会被销毁
        mPager.setOffscreenPageLimit(1);
        mPager.setScrollEnabled(false);
        mPager.setAdapter(mAdapter);
        mPager.setEnabled(false);

        TabContainerView mTabLayout = (TabContainerView) findViewById(R.id.ll_tab_container);
        mTabLayout.setOnPageChangeListener(this);

        mTabLayout.initContainer(getResources().getStringArray(R.array.tab_main_title), ICONS_RES, TAB_COLORS, true);

        int width = getResources().getDimensionPixelSize(R.dimen.tab_icon_width);
        int height = getResources().getDimensionPixelSize(R.dimen.tab_icon_height);
        mTabLayout.setContainerLayout(R.layout.tab_container_view, R.id.iv_tab_icon, R.id.tv_tab_text, width, height);

        mTabLayout.setViewPager(mPager);

        mPager.setCurrentItem(getIntent().getIntExtra("tab", 0));

    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

//    public void start1(View view){
//        Intent intent = new Intent();
//        intent.setClass(HomeActivity.this, CollectionEnumgGnericsActivity.class);
//        HomeActivity.this.startActivity(intent);
//    }
//
//    public void start2(View view){
//        Intent intent = new Intent();
//        intent.setClass(HomeActivity.this, HttpURLConnectionActivity.class);
//        HomeActivity.this.startActivity(intent);
//    }
//
//    public void start3(View view){
//        Intent intent = new Intent();
//        intent.setClass(HomeActivity.this, XMLtoEntityActivity.class);
//        HomeActivity.this.startActivity(intent);
//    }

}
