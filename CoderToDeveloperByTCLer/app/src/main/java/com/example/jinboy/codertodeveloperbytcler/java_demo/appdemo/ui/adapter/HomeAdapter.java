package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.AbsBaseEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.BannerEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.MenuEntrty;

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
    private final int TYPE_JAVA = 2;          //JavaDemo页面


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
        } else if (entity instanceof MenuEntrty) {
            return TYPE_MENU;
//        } else if (entity instanceof JavaEntity) {
//            return  TYPE_JAVA;
//        } else if (entity instanceof SaleEntity) {
//            return  TYPE_SALE;
//        } else if (entity instanceof FansEntity) {
//            return  TYPE_FANS;
//        } else if (entity instanceof ServiceEntity) {
//            return  TYPE_SERVICE;
//        } else if (entity instanceof YuqingEntity) {
//            return  TYPE_YUQING;
//        } else if (entity instanceof FinanceHomeEntity) {
//            return TYPE_FINANCE;
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
//                case TYPE_KPI:     //KPI
//                    return new KPIItemViewHolder(mContext, mInflater.inflate(R.layout.home_kpi_item, parent, false));
//                case TYPE_SALE:    //销售
//                    return new SaleItemViewHolder(mContext, mInflater.inflate(R.layout.home_sales_item, parent, false));
//
//                case TYPE_FANS:   //粉丝
//                    return new FansItemViewHolder(mContext, mInflater.inflate(R.layout.home_fans_item, parent, false));
//
//                case TYPE_SERVICE:  //服务
//                    return new ServiceItemViewHolder(mContext, mInflater.inflate(R.layout.home_service_item, parent, false));
//
//                case TYPE_YUQING:    //舆情
//                    return new YuqingItemViewHolder(mContext, mInflater.inflate(R.layout.home_yuqing_item, parent, false));
//
//                case TYPE_FINANCE:  //财务
//                    return new FinanceItemViewHolder(mContext, mInflater.inflate(R.layout.home_finance_item, parent, false));
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
