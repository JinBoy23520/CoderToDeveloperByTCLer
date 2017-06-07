package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.AbsBaseEntity;

import java.text.DecimalFormat;


/**
 * 主页适配器item处理基类
 */

abstract class BaseItemViewHolder<T extends AbsBaseEntity> extends RecyclerView.ViewHolder {
    DecimalFormat mFormat;
    Context mContext;
    /** 标记线 */
    private View mHeadLineV;
    /** 模块名 */
    private TextView mHeadNameTv;
    /** 时间段  后期可能会换名称*/
    private TextView mHeadTimeTv;
    /**头部view*/
    private LinearLayout mHeadContainerLl;

    BaseItemViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        mHeadLineV = itemView.findViewById(R.id.home_head_item_line);
        mHeadNameTv = (TextView) itemView.findViewById(R.id.tv_home_head_item_name);
        mHeadTimeTv = (TextView) itemView.findViewById(R.id.tv_home_head_item_time);
        mHeadContainerLl = (LinearLayout) itemView.findViewById(R.id.ll_head_container);
        mFormat = new DecimalFormat("#,###.##");
    }

    public abstract void onBindViewHolder(T entity);

    void updateHeadInfos(T entity, int lineColor,String titleRes) {
        mHeadLineV.setBackgroundColor(mContext.getResources().getColor(lineColor));
        mHeadNameTv.setText(titleRes);
        setHeadViewOnClickListener(entity, mHeadContainerLl);
    }

    private void setHeadViewOnClickListener(final T entity, View view) {
        if (view == null) {
            return;
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHeadViewClick(entity);
            }
        });
    }

    /**
     * 头部被点击
     * @param entity T
     */
    void onHeadViewClick(T entity){
    }
}
