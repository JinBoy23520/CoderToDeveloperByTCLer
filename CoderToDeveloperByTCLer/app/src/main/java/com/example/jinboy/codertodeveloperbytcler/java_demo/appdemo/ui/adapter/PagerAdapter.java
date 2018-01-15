package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.fragment.BlogFragment;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.util.Constants;


/**
 * 作者： 叶应是叶
 * 时间： 2017/3/20 10:42
 * 描述： 页面适配器
 */
public class PagerAdapter extends FragmentPagerAdapter {

    /**
     * 标签
     */
    public static String[] TAGS = new String[]{"最新", Constants.DIRECTORY_1, Constants.DIRECTORY_2,
            Constants.DIRECTORY_3, Constants.DIRECTORY_4, Constants.DIRECTORY_5, Constants.DIRECTORY_6};

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return BlogFragment.getBlogFragment(position);
    }

    @Override
    public int getCount() {
        return TAGS.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TAGS[position];
    }

}
