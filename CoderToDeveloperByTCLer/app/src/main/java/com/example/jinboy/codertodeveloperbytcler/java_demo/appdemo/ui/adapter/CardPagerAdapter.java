package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.CardItem;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.WebViewActivity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.util.StringUtils;
import com.squareup.picasso.Picasso;
import java.util.List;

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private Context mContext;
    private List<CardItem> mCardList;

    private View[] mViews;

    private float mBaseElevation;

    public CardPagerAdapter(Context context, List<CardItem> mCardList) {
        this.mContext = context;
        this.mCardList = mCardList;
        initView();
    }

    private void initView() {
        mViews = new View[mCardList.size()];
        LayoutInflater inflater = LayoutInflater.from(mContext);
        for (int index = 0, len = mCardList.size(); index < len; index++) {
            mViews[index] = inflater.inflate(R.layout.card_adapter, null, false);
        }
    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return (CardView) mViews[position];
    }

    @Override
    public int getCount() {
        return mCardList.size();
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
        int position = arg1 % mCardList.size();
        arg0.removeView(mViews[position]);
    }

    @Override
    public Object instantiateItem(ViewGroup arg0, int arg1) {
        int position = arg1 % mCardList.size();
        ImageView view = (ImageView) mViews[position].findViewById(R.id.img_cardview);
        String url = mCardList.get(position).getImagUrl();
        if (null != url && !url.equals("")) {
            Picasso.with(mContext)
                    .load(url)
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

        CardView cardView = (CardView)  mViews[position].findViewById(R.id.cardView);
        TextView titleTextView = (TextView)  mViews[position].findViewById(R.id.titleTextView);
        TextView contentTextView = (TextView)  mViews[position].findViewById(R.id.contentTextView);
        titleTextView.setText(mCardList.get(position).getTitle());
        contentTextView.setText("选课人数"+ "\n" +mCardList.get(position).getPeopleNum());

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);

        setOnClickListener(mViews[position].findViewById(R.id.cardView), position);
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
                if (!StringUtils.isEmpty(mCardList.get(index).getWebUrl())){
                    WebViewActivity.startWebViewActivity(mContext, mCardList.get(index).getWebUrl());
                }
            }
        };
        view.setOnClickListener(listener);
    }

}
