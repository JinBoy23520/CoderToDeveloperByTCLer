package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.RecyclerEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.fragment.FirstFragment;

import java.text.DecimalFormat;
import java.util.List;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/07/07
 *     desc   : 报数页面下拉加载更多Adapter
 *     version: 1.0
 * </pre>
 */

public class UpDownRefreshAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private FirstFragment firstFragment;
    private List<RecyclerEntity> records;
    boolean isNeedSetBgColor = true;
    boolean isGone = true ;
    private DecimalFormat mFormat;

    /**
     * 构造方法，把用到的值带过来，数据，可控制尾部加载提醒是否显示
     * @param firstFragment
     * @param records
     * @param isGone
     */
    public UpDownRefreshAdapter(FirstFragment firstFragment, List<RecyclerEntity> records,boolean isGone ) {
        this.firstFragment = firstFragment;
        this.records = records;
        this.isGone = isGone;
        mFormat = new DecimalFormat("#,###.##");
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        if (records != null){
            return records.size() == 0 ? 0 : records.size() + 1;
        }else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    /**
     * 绑定数据显示栏和尾部提醒加载栏的布局
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(firstFragment.getContext()).inflate(R.layout.up_down_refresh_data_layout, parent,
                    false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(firstFragment.getContext()).inflate(R.layout.item_foot, parent,
                    false);
            return new FootViewHolder(view);
        }
        return null;
    }

    /**
     * 设置显示数据处理，控制布局背景等，底部提醒是否显示
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ItemViewHolder) {
            if (records != null) {
                RecyclerEntity recyclerEntity = records.get(position);
                ((ItemViewHolder) holder).tv_category.setText(recyclerEntity.getCategory());
                ((ItemViewHolder) holder).tv_page.setText(mFormat.format(Double.valueOf(recyclerEntity.getPages())));
                ((ItemViewHolder) holder).tv_sales.setText(mFormat.format(Double.valueOf(recyclerEntity.getSales())));
                if (isNeedSetBgColor == false) {
                    ((ItemViewHolder) holder).ll_recyclerData_item.setBackgroundColor(Color.parseColor("#ffffff"));
                } else {
                    ((ItemViewHolder) holder).ll_recyclerData_item.setBackgroundColor(Color.parseColor("#fafafa"));
                }
                isNeedSetBgColor = !isNeedSetBgColor;
            }

            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, position);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemLongClick(holder.itemView, position);
                        return false;
                    }
                });
            }
        }

        if (holder instanceof FootViewHolder) {
            if (isGone) {
                ((FootViewHolder) holder).ll_item_loading.setVisibility(View.GONE);
            }
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_category,tv_page,tv_sales;
        public LinearLayout ll_recyclerData_item;
        public ItemViewHolder(View view) {
            super(view);
            tv_category = (TextView) view.findViewById(R.id.tv_category);
            tv_page = (TextView) view.findViewById(R.id.tv_page);
            tv_sales = (TextView) view.findViewById(R.id.tv_sales);
            ll_recyclerData_item = (LinearLayout) view.findViewById(R.id.ll_recyclerData_item);
        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout ll_item_loading;
        public FootViewHolder(View view) {
            super(view);
            ll_item_loading = (LinearLayout) view.findViewById(R.id.ll_item_loading);
        }
    }
}
