package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.AbsBaseEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.AndroidUIEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.BannerEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.JavaEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.MenuEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.MyViewEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.RecyclerViewEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    /**下拉刷新view*/
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView mRecyclerView;

    private List<AbsBaseEntity> mEntities = new ArrayList<>();

    private HomeAdapter homeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        intData();
        initView();
    }

    public void intData(){
        BannerEntity bannerEntity = new BannerEntity();
        bannerEntity.parseData();
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.parseData();
        JavaEntity javaEntity = new JavaEntity();
        javaEntity.parseData();
        AndroidUIEntity androidUIEntity = new AndroidUIEntity();
        androidUIEntity.parseData();
        RecyclerViewEntity recyclerViewEntity = new RecyclerViewEntity();
        recyclerViewEntity.parseData();
        MyViewEntity myViewEntity = new MyViewEntity();
        myViewEntity.parseData();
        mEntities.add(bannerEntity);
        mEntities.add(menuEntity);
        mEntities.add(javaEntity);
        mEntities.add(androidUIEntity);
        mEntities.add(recyclerViewEntity);
        mEntities.add(myViewEntity);
    }

    public void initView () {
        mSwipeRefreshLayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipe_refresh_layout);
        //★1.设置刷新时动画的颜色，可以设置4个
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                R.color.blueLight,
                R.color.btn_cm_bg_pressed,
                R.color.withe);
        //★2.设置刷新监听事件
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //RecyclerView简单步骤
        homeAdapter = new HomeAdapter(getActivity(), mEntities);
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.lv_home_listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(homeAdapter);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEntities.clear();
                intData();
                //★3.通知recycleView改变了数据
                homeAdapter.notifyDataSetChanged();
                //★4.记得关闭刷新，否则刷新球一直在转
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 50);
    }
}
