package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.AndroidUIEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.JavaEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.MyViewEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.AndroidUIActivity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.MyViewActivity;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/06/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MyViewItemViewHolder extends BaseItemViewHolder<MyViewEntity> {
    private TextView mItemNameTv;
    private TextView mItemIntroTv;

    MyViewItemViewHolder(Context context, View itemView) {
        super(context, itemView);
        mItemNameTv = (TextView) itemView.findViewById(R.id.tv_home_head_item_name);
        mItemIntroTv = (TextView) itemView.findViewById(R.id.tv_intro);
    }

    @Override
    public void onBindViewHolder(MyViewEntity entity) {
        updateHeadInfos(entity,R.color.title_line2,entity.getTitle());
        mItemIntroTv.setText(entity.getIntro());
    }

    @Override
    void onHeadViewClick(MyViewEntity entity) {
        super.onHeadViewClick(entity);
        Intent intent = new Intent(mContext,MyViewActivity.class);
        mContext.startActivity(intent);
    }
}
