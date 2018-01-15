package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.Common;

import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 作者： 叶应是叶
 * 时间： 2017/3/20 10:08
 * 描述： 通用RecyclerView ViewHolder
 */
public class CommonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    // SparseArray比HashMap更省内存，在某些条件下性能更好，只能存储key为int类型的数据
    // 用来存放View以减少findViewById的次数
    private SparseArray<View> viewSparseArray;

    public interface onItemCommonClickListener {

        void onItemClickListener(int position);

        void onItemLongClickListener(int position);

    }

    private onItemCommonClickListener commonClickListener;

    CommonViewHolder(View itemView) {
        super(itemView);
        viewSparseArray = new SparseArray<>();
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    /**
     * 根据 ID 来获取 View
     *
     * @param viewId viewID
     * @param <T>    泛型
     * @return 将结果强转为 View 或 View 的子类型
     */
    public <T extends View> T getView(int viewId) {
        // 先从缓存中找，找到的话则直接返回
        // 如果找不到则findViewById，再把结果存入缓存中
        View view = viewSparseArray.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            viewSparseArray.put(viewId, view);
        }
        return (T) view;
    }

    public CommonViewHolder setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public CommonViewHolder setText(int viewId, SpannableString spannableString) {
        TextView textView = getView(viewId);
        textView.setText(spannableString);
        return this;
    }

    public CommonViewHolder setViewVisibility(int viewId, int visibility) {
        getView(viewId).setVisibility(visibility);
        return this;
    }

    public CommonViewHolder setImageResource(int viewId, int resourceId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resourceId);
        return this;
    }

    public CommonViewHolder setImageViewPadding(int viewId, int left, int top, int right, int bottom) {
        ImageView imageView = getView(viewId);
        imageView.setPadding(left, top, right, bottom);
        return this;
    }

    public void setImageViewOnClickListener(int viewId, View.OnClickListener onClickListener) {
        ImageView imageView = getView(viewId);
        imageView.setOnClickListener(onClickListener);
    }

    public void setTextViewOnLongClickListener(int viewId, View.OnLongClickListener onLongClickListener) {
        TextView textView = getView(viewId);
        textView.setOnLongClickListener(onLongClickListener);
    }

    public void setCommonClickListener(onItemCommonClickListener commonClickListener) {
        this.commonClickListener = commonClickListener;
    }

    @Override
    public void onClick(View v) {
        if (commonClickListener != null) {
            commonClickListener.onItemClickListener(getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (commonClickListener != null) {
            commonClickListener.onItemLongClickListener(getAdapterPosition());
        }
        return false;
    }

}
