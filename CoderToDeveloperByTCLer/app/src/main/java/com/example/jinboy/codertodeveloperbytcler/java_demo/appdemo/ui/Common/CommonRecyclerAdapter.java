package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.Common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 作者： 叶应是叶
 * 时间： 2017/3/20 10:07
 * 描述： 通用RecyclerView Adapter
 */
public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {

    private LayoutInflater layoutInflater;

    protected List<T> dataList;

    private int layoutId;

    public interface MultiTypeSupport<T> {
        int getLayoutId(T item, int position);
    }

    protected MultiTypeSupport<T> multiTypeSupport;

    protected CommonViewHolder.onItemCommonClickListener commonClickListener;

    protected CommonRecyclerAdapter(Context context, List<T> dataList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.dataList = dataList;
    }

    protected CommonRecyclerAdapter(Context context, List<T> dataList, int layoutId) {
        this.layoutInflater = LayoutInflater.from(context);
        this.dataList = dataList;
        this.layoutId = layoutId;
    }

    protected CommonRecyclerAdapter(Context context, List<T> dataList, int layoutId,
                                    CommonViewHolder.onItemCommonClickListener commonClickListener) {
        this(context, dataList, layoutId);
        this.commonClickListener = commonClickListener;
    }

    protected CommonRecyclerAdapter(Context context, List<T> dataList, MultiTypeSupport<T> multiTypeSupport) {
        this(context, dataList);
        this.multiTypeSupport = multiTypeSupport;
    }

    protected CommonRecyclerAdapter(Context context, List<T> dataList, MultiTypeSupport<T> multiTypeSupport,
                                    CommonViewHolder.onItemCommonClickListener commonClickListener) {
        this(context, dataList, multiTypeSupport);
        this.commonClickListener = commonClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (multiTypeSupport != null) {
            return multiTypeSupport.getLayoutId(dataList.get(position), position);
        }
        return super.getItemViewType(position);
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (multiTypeSupport != null) {
            layoutId = viewType;
        }
        View itemView = layoutInflater.inflate(layoutId, parent, false);
        return new CommonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        bindData(holder, dataList.get(position));
        holder.setCommonClickListener(commonClickListener);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    protected abstract void bindData(CommonViewHolder holder, T data);

}
