package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.MenuEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.AndroidDemoActivity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.AndroidUIActivity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.JavaDemoActivity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.MixedActivity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.MyViewActivity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.RecyclerViewActivity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.androiddemo.FileOperationsActivity;


/**
 * 菜单栏
 * Created by 坚果-王健(wangjian3@kuyumall.com) on 2016/12/1.
 */

public class MenuItemViewHolder extends BaseItemViewHolder<MenuEntity> {
    private LinearLayout mContainerLl;

    MenuItemViewHolder(Context context, View itemView) {
        super(context, itemView);
        mContainerLl = (LinearLayout) itemView.findViewById(R.id.ll_home_container);
    }

    @Override
    public void onBindViewHolder(MenuEntity entity) {

        mContainerLl.removeAllViews();

        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);

        float itemWidth = point.x / 4.5f;


        for (int index = 0, len = entity.getmItemEntities().size(); index < len; index ++) {
            MenuEntity.MenuItemEntity itemEntity = entity.getmItemEntities().get(index);
            autoAddMenuItemView(entity, itemWidth, itemEntity.getImgId(), itemEntity.getTitle());
        }
    }

    private  void autoAddMenuItemView(final MenuEntity entity, float width, int imageResId, String textResId) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_menu_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_home_menu);
        TextView textView = (TextView) view.findViewById(R.id.tv_home_menu);
        imageView.setImageResource(imageResId);
        textView.setText(textResId);
        view.setId(imageResId);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (params == null) {
            params = new LinearLayout.LayoutParams((int) width, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        params.width = (int) width;
        view.setLayoutParams(params);
                view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.drawable.java_img:
                        Intent intent = new Intent(mContext,JavaDemoActivity.class);
                        mContext.startActivity(intent);
                        break;
                    case R.drawable.ui_img:
                        Intent intent1 = new Intent(mContext,AndroidUIActivity.class);
                        mContext.startActivity(intent1);
                        break;
                    case R.drawable.recyclerview_img:
                        Intent intent2 = new Intent(mContext,RecyclerViewActivity.class);
                        mContext.startActivity(intent2);
                        break;
                    case R.drawable.view_img:
                        Intent intent3 = new Intent(mContext,MyViewActivity.class);
                        mContext.startActivity(intent3);
                        break;
                    case R.drawable.android_img:
                        Intent intent4 = new Intent(mContext,AndroidDemoActivity.class);
                        mContext.startActivity(intent4);
                    case R.drawable.mixed_icon:
                        Intent intent5 = new Intent(mContext,MixedActivity.class);
                        mContext.startActivity(intent5);
                }
            }
        });

        mContainerLl.addView(view);
    }

}
