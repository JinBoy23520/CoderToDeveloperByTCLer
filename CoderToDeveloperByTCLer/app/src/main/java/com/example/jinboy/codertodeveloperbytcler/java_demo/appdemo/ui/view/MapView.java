package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.MapViewEntity;

import java.text.DecimalFormat;
import java.util.List;


/**
 * Created by zhangchanghua on 2017/6/15.
 */

public class MapView extends LinearLayout {
    private DecimalFormat format=new DecimalFormat("###,###,###");
    public MapView(Context context) {
        super(context);
        setOrientation(HORIZONTAL);
    }

    public MapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
    }

    public MapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
    }

    int bgHeight = 0, bgWidth = 0;

    public void init(List<MapViewEntity.Area> datas) {
        removeAllViews();
        if (datas == null) {
            return;
        }
        //左侧背景图
        ImageView imageView = new ImageView(getContext());
        Bitmap bg = BitmapFactory.decodeResource(getResources(), R.mipmap.map_bg);

        if (bg != null) {
            imageView.setImageBitmap(bg);
            bgHeight = bg.getHeight();
            bgWidth = bg.getWidth();
        }
        addView(imageView);
        final LinearLayout itemContainer = new LinearLayout(getContext());
        itemContainer.setOrientation(VERTICAL);
        for (int i = 0; i < datas.size(); i++) {
            View item = LayoutInflater.from(getContext()).inflate(R.layout.map_view_item, itemContainer, false);
            TextView indexView = (TextView) item.findViewById(R.id.index);
            TextView textView = (TextView) item.findViewById(R.id.text);
            GradientDrawable indexDrawable = (GradientDrawable) indexView.getBackground();
            int color = Color.parseColor("#FA7401");
            switch (i) {
                case 0:
                    color = Color.parseColor("#FA7401");
                    break;
                case 1:
                    color = Color.parseColor("#D2007F");
                    break;
                case 2:
                    color = Color.parseColor("#006FBF");
                    break;
                case 3:
                    color = Color.parseColor("#009C85");
                    break;
                case 4:
                    color = Color.parseColor("#8FC41E");
                    break;
            }
            indexDrawable.setColor(color);
            indexView.setBackground(indexDrawable);
            MapViewEntity.Area area=datas.get(i);
            indexView.setText(area.ranking + "");
            textView.setTextColor(color);
            textView.setText(area.areaName+"  "+format.format(area.areaCount));
            itemContainer.addView(item);
        }
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = -bgWidth;
        addView(itemContainer, lp);
        //修正位置(瞎计算)
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                float degree0 = (float) Math.sin(Math.PI * 30.0 / 180.0);
                float degree1 = (float) Math.sin(Math.PI * 60.0 / 180.0);
                float degree2 = (float) Math.sin(Math.PI * 90.0 / 180.0);
                int shift = (int) (getResources().getDisplayMetrics().density * 7);
                for (int i = 0; i < itemContainer.getChildCount(); i++) {
                    final View item = itemContainer.getChildAt(i);
                    LayoutParams lp = (LayoutParams) item.getLayoutParams();
                    switch (i) {
                        case 0:
                            lp.leftMargin = (int) (bgWidth * degree0) + shift * 4;
                            lp.topMargin = (int) (bgHeight * degree0) / 22;
                            break;
                        case 1:
                            lp.leftMargin = (int) (bgWidth * degree1) + shift * 2;
                            lp.topMargin = (int) (bgHeight * degree1) / 16;
                            break;
                        case 2:
                            lp.leftMargin = (int) (bgWidth * degree2) + shift;
                            lp.topMargin = (int) (bgHeight * degree2) / 8;
                            break;
                        case 3:
                            lp.leftMargin = (int) (bgWidth * degree1) + shift * 2;
                            lp.topMargin = (int) (bgHeight * degree2) / 8;
                            break;
                        case 4:
                            lp.leftMargin = (int) (bgWidth * degree0) + shift * 4;
                            lp.topMargin = (int) (bgHeight * degree1) / 16;
                            break;
                    }
                    item.setLayoutParams(lp);
                    item.setVisibility(View.GONE);
                    item.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ObjectAnimator anim = ObjectAnimator.ofFloat(item, "translationX", -bgWidth, 0);
                            anim.setDuration(1000);
                            anim.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationStart(Animator animation) {
                                    super.onAnimationStart(animation);
                                    item.setVisibility(View.VISIBLE);
                                }
                            });
                            anim.start();
                        }
                    }, (i + 1) * 300);
                }
                getViewTreeObserver().removeOnPreDrawListener(this);
                return false;
            }
        });
    }
}
