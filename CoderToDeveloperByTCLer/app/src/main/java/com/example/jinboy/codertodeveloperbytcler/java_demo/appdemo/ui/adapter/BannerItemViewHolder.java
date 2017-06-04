package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.BannerEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.view.ViewPagerInViewPager;


/**
 * 广告模块
 * Created by 坚果-王健(wangjian3@kuyumall.com) on 2016/12/1.
 */

public class BannerItemViewHolder extends BaseItemViewHolder<BannerEntity> {

    /** banner适配器*/
    private ViewPagerInViewPager mViewPagerInViewPager;
    /** banner指示器*/
    private LinearLayout mIndicatorLl;
    /** 指示器内容*/
    private ImageView[] mImageViews;

    public BannerItemViewHolder(Context context, View itemView) {
        super(context, itemView);
        mViewPagerInViewPager = (ViewPagerInViewPager) itemView.findViewById(R.id.vp_home_banner);
        mIndicatorLl = (LinearLayout) itemView.findViewById(R.id.ll_home_banner_indicator);
    }

    @Override
    public void onBindViewHolder(BannerEntity entity) {
        mIndicatorLl.removeAllViews();
        BannerViewPagerAdapter imgViewPagerAdapter = new BannerViewPagerAdapter(mContext, entity.getBannerItems());
        mViewPagerInViewPager.setAdapter(imgViewPagerAdapter);

        if (entity.getBannerItems().size() == 1) { //一张图时不做切换操作
            return;
        }
        initIndicatorView(entity);
        mViewPagerInViewPager.clearOnPageChangeListeners();
        mViewPagerInViewPager.addOnPageChangeListener(new GuidePageChangeListener(mImageViews));
    }


    /**
     * <li>初始化广告切换指引view</li>
     */
    private void initIndicatorView(BannerEntity entity) {
        mImageViews = new ImageView[entity.getBannerItems().size()];

        for (int i = 0, len = entity.getBannerItems().size(); i < len; i++) {
            ImageView imageView = new ImageView(mContext);
            int piexSize = mContext.getResources().getDimensionPixelSize(R.dimen.banner_indicator_size);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(piexSize, piexSize));
            int piexPadding = mContext.getResources().getDimensionPixelSize(R.dimen.guide_indicator_padding);
            mImageViews[i] = imageView;
            mImageViews[i].setBackgroundResource(R.drawable.shape_circle_indicator_press);
            if (i != 0) {
                mImageViews[i].setBackgroundResource(R.drawable.shape_circle_indicator_normal);
            }
            if (i != 0) {
                TextView nullView = new TextView(mContext);
                nullView.setLayoutParams(new LinearLayout.LayoutParams(piexPadding, piexPadding));
                mIndicatorLl.addView(nullView);
            }
            mIndicatorLl.addView(mImageViews[i]);
        }
    }

    /**
     * viewpager滑动监听
     */
    private class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        private ImageView[] mImageViews;

        GuidePageChangeListener(ImageView[] imageViews) {
            this.mImageViews = imageViews;
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int arg0) {
            for (int i = 0; i < mImageViews.length; i++) {
                mImageViews[arg0].setBackgroundResource(R.drawable.shape_circle_indicator_press);
                if (arg0 != i) {
                    mImageViews[i].setBackgroundResource(R.drawable.shape_circle_indicator_normal);
                }
            }
        }
    }

}
