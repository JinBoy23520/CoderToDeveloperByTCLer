package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.fragment.FirstFragment;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.fragment.SecondFragment;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

public class SwipeRefreshRecyclerViewActivity extends FragmentActivity implements View.OnClickListener{
    private List<Fragment> listFragment;
    private RadioGroup rGrpAllTabP;
    private FirstFragment reportDataFragment = new FirstFragment(); //品类页面
    private SecondFragment historyReportDataFragment = new SecondFragment(); //品类渠道页面
    private RadioButton rBtn_fansTab,rBtn_memberTab;
    private static CustomViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
        setUpViewPager();
    }

    public void initView() {
        setContentView(R.layout.swipe_refresh_recycler_view_activity);
        rGrpAllTabP = (RadioGroup)findViewById(R.id.rGrpAllTabP);
        rBtn_memberTab = (RadioButton)findViewById(R.id.rBtn_memberTab);
        rBtn_fansTab = (RadioButton)findViewById(R.id.rBtn_fansTab);
    }

    public void initListener() {
        rGrpAllTabP.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int position = 0;
                switch (checkedId) {
                    case R.id.rBtn_fansTab:
                        position = 0;
                        break;
                    case R.id.rBtn_memberTab:
                        position = 1;
                        break;
                }
                rGrpAllTabP.check(checkedId);
                if (mViewPager != null) mViewPager.setCurrentItem(position, true);
            }
        });
        listFragment = new ArrayList<>();
        listFragment.add(reportDataFragment);
        listFragment.add(historyReportDataFragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return listFragment.get(position);
            }

            @Override
            public int getCount() {
                return listFragment.size();
            }
        };
        mViewPager = (CustomViewPager)findViewById(R.id.pager);
        mViewPager.setAdapter(fragmentPagerAdapter);
        mViewPager.setOffscreenPageLimit(listFragment.size());
    }

    public void toHistoryReportDataFragment(){
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                mViewPager.setCurrentItem(1);
            }
        }, 1000);
    }

    private void setUpViewPager() {
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                int checkId = R.id.rBtn_fansTab;
                if (position == 0) {
                    checkId = R.id.rBtn_fansTab;
                } else if (position == 1) {
                    checkId = R.id.rBtn_memberTab;
                }
                if (rGrpAllTabP != null)
                    rGrpAllTabP.check(checkId);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                switch (state) {
//                    case ViewPager.SCROLL_STATE_IDLE:
//                        //TODO
//                        break;
//                    case ViewPager.SCROLL_STATE_DRAGGING:
//                        //TODO
//                        break;
//                    case ViewPager.SCROLL_STATE_SETTLING:
//                        //TODO
//                        break;
//                    default:
//                        //TODO
//                        break;
//                }
            }
        });
        if (mViewPager != null) mViewPager.setCurrentItem(0, true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.ll_addAcBack:
//                finish();
//                break;
            default:
                break;
        }
    }

}
