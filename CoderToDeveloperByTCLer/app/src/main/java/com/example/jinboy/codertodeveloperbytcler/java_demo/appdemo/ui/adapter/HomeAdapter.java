package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.AbsBaseEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.AndroidUIEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.BannerEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.JavaEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.MenuEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.MixedEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.MyViewEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.RecyclerViewEntity;

import java.util.List;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/05/31
 *     desc   : RecyclerView实现首页adapter
 *     version: 1.0
 * </pre>
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    //布局标识集合
    private final List<AbsBaseEntity> typeList;
    private LayoutInflater mInflater;
    //设置两个常量
    private final int TYPE_BANNER = 0;        //广告位
    private final int TYPE_MENU = 1;          //菜单
    private final int TYPE_JAVA = 2;          //JavaDemo 页面
    private final int TYPE_ANDRIOD_UI = 3;    //Android UI 页面
    private final int TYPE_RECYCLERVIEW = 4;    //RecyclerViewDemo 页面
    private final int TYPE_MYVIEW = 5;    //自定义View 页面
    private final int MIXED_CODE = 6;    //自定义View 页面

    public HomeAdapter(Context context, List<AbsBaseEntity> typeList) {
        this.context = context;
        this.typeList = typeList;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        AbsBaseEntity entity = typeList.get(position);
        if (entity instanceof BannerEntity) {
            return TYPE_BANNER;
        } else if (entity instanceof MenuEntity) {
            return TYPE_MENU;
        } else if (entity instanceof JavaEntity) {
            return TYPE_JAVA;
        } else if (entity instanceof AndroidUIEntity) {
            return TYPE_ANDRIOD_UI;
        } else if (entity instanceof RecyclerViewEntity) {
            return TYPE_RECYCLERVIEW;
        } else if (entity instanceof MyViewEntity) {
            return TYPE_MYVIEW;
        } else if (entity instanceof MixedEntity) {
            return MIXED_CODE;
        } else {
            return 0;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case TYPE_BANNER:  //广告
                return new BannerItemViewHolder(context, mInflater.inflate(R.layout.home_banner_view, parent, false));
            case TYPE_MENU:    //菜单
                return new MenuItemViewHolder(context, mInflater.inflate(R.layout.home_menu_container, parent, false));
            case TYPE_JAVA:     //JavaDemo
                return new JavaItemViewHolder(context, mInflater.inflate(R.layout.home_java_item, parent, false));
            case TYPE_ANDRIOD_UI:    //Android UI 页面
                return new AndroidUIItemViewHolder(context, mInflater.inflate(R.layout.home_java_item, parent, false));

            case TYPE_RECYCLERVIEW:   //RecyclerViewDemo
                return new RecyclerViewItemViewHolder(context, mInflater.inflate(R.layout.home_java_item, parent, false));

            case TYPE_MYVIEW:  //自定义View
                return new MyViewItemViewHolder(context, mInflater.inflate(R.layout.home_java_item, parent, false));

            case MIXED_CODE:  //混合开发demo
                return new MixedItemViewHolder(context, mInflater.inflate(R.layout.home_java_item, parent, false));
        }
        return null;
    }

    /**
     * 这里抽取一层，继承BaseItemViewHolder，分别处理
     *
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        BaseItemViewHolder baseViewHolder = (BaseItemViewHolder) viewHolder;
        baseViewHolder.onBindViewHolder(typeList.get(position));
    }

    @Override
    public int getItemCount() {
        return typeList.size();
    }
}
