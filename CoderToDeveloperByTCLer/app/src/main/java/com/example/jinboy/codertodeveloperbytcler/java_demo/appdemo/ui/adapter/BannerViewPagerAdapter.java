package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.adapter;

/**
 * Created by 坚果-王健(wangjian3@kuyumall.com) on 2016/11/30.
 */

/**  */

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.BannerEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.WebViewActivity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.util.StringUtils;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * <li>广告图适配器 </li>
 */
public class BannerViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<BannerEntity.BannerItem> mBannerList;
    private View[] mViews;


    public BannerViewPagerAdapter(Context context, List<BannerEntity.BannerItem> photos) {
        this.mContext = context;
        this.mBannerList = photos;
        initView();
    }


    private void initView() {
        mViews = new View[mBannerList.size()];
        LayoutInflater inflater = LayoutInflater.from(mContext);
        for (int index = 0, len = mBannerList.size(); index < len; index++) {
            mViews[index] = inflater.inflate(R.layout.home_banner_item, null, false);
        }
    }

    @Override
    public int getCount() {
        return mBannerList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public void destroyItem(ViewGroup arg0, int arg1, Object arg2) {
        int position = arg1 % mBannerList.size();
        arg0.removeView(mViews[position]);
    }

    @Override
    public Object instantiateItem(ViewGroup arg0, int arg1) {
        int position = arg1 % mBannerList.size();
        ImageView view = (ImageView) mViews[position].findViewById(R.id.iv_active_banner_item);
        String url = mBannerList.get(position).getImageUrl();
        if (null != url && !url.equals("")) {
            Picasso.with(mContext)
                    .load(url)
//                    .resize(view.getMeasuredWidth(),view.getMeasuredHeight())
//                    .centerInside()
                    .fit()
                    .into(view);
        }
        try {
            if (mViews[position].getParent() == null) {
                arg0.addView(mViews[position], 0);
            } else {
                ((ViewGroup) mViews[position].getParent()).removeAllViews();

                arg0.addView(mViews[position], 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setOnClickListener(mViews[position].findViewById(R.id.ll_active_banner_item), position);
        return mViews[position];
    }


    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }


    @Override
    public Parcelable saveState() {
        return null;
    }


    private void setOnClickListener(View view, final int index) {
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
             if (!StringUtils.isEmpty(mBannerList.get(index).getWebUrl())){
                    WebViewActivity.startWebViewActivity(mContext, mBannerList.get(index).getWebUrl());
                }
            }
        };
        view.setOnClickListener(listener);
    }

}
