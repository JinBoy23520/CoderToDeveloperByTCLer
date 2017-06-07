package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/06/05
 *     desc   : 菜单栏实体类
 *     version: 1.0
 * </pre>
 */

public class MenuEntity extends AbsBaseEntity {
    private List<MenuItemEntity> mItemEntities = new ArrayList<>();

    public List<MenuItemEntity> getmItemEntities() {
        return mItemEntities;
    }

    public void setmItemEntities(List<MenuItemEntity> mItemEntities) {
        this.mItemEntities = mItemEntities;
    }

    @Override
    public void parseData() {
        MenuItemEntity menuItemEntity = new MenuItemEntity();
        menuItemEntity.setTitle("Java");
        menuItemEntity.setImgId(R.drawable.java_img);
        mItemEntities.add(menuItemEntity);
        MenuItemEntity menuItemEntity1 = new MenuItemEntity();
        menuItemEntity1.setTitle("UI");
        menuItemEntity1.setImgId(R.drawable.ui_img);
        mItemEntities.add(menuItemEntity1);
        MenuItemEntity menuItemEntity2 = new MenuItemEntity();
        menuItemEntity2.setTitle("RecyclerView");
        menuItemEntity2.setImgId(R.drawable.recyclerview_img);
        mItemEntities.add(menuItemEntity2);
        MenuItemEntity menuItemEntity3 = new MenuItemEntity();
        menuItemEntity3.setTitle("MyView");
        menuItemEntity3.setImgId(R.drawable.view_img);
        mItemEntities.add(menuItemEntity3);
        MenuItemEntity menuItemEntity4 = new MenuItemEntity();
        menuItemEntity4.setTitle("RecyclerView");
        menuItemEntity4.setImgId(R.drawable.recyclerview_img);
        mItemEntities.add(menuItemEntity4);
        MenuItemEntity menuItemEntity5 = new MenuItemEntity();
        menuItemEntity5.setTitle("RecyclerView");
        menuItemEntity5.setImgId(R.drawable.recyclerview_img);
        mItemEntities.add(menuItemEntity5);
    }

    public static class MenuItemEntity {
        private int imgId;
        private String title;

        public int getImgId() {
            return imgId;
        }

        public void setImgId(int imgId) {
            this.imgId = imgId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
