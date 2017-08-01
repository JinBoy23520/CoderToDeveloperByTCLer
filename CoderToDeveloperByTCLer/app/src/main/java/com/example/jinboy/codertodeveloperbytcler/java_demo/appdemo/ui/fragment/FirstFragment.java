package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.RecyclerEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.recyclerview.EndlessRecyclerOnScrollListener;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.adapter.UpDownRefreshAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *  SwipeRefreshLayout+RecyclerView实现下拉刷新上拉自动加载
 */
public class FirstFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    /**
     * 下拉刷新view
     */
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView;
    private View view;
    boolean isLoading = true;
    /**
     * 当前页
     */
    private int current;
    /**
     * 总页数
     */
    private int pages = 4;
    private List<RecyclerEntity> records = new ArrayList<>();
    boolean isFirst = true;
    private UpDownRefreshAdapter upDownRefreshAdapter;
    private RecyclerEntity recyclerEntity = new RecyclerEntity();
    private EndlessRecyclerOnScrollListener mOnScrollListener;

    private TabLayout tabLayout;
    private List<String> list = new ArrayList();
    /**标记选择位置**/
    private int itemSelected = 0;
    private String name = "全部";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.first_fragment, container, false);
        intView();
        return view;
    }

    public void intView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        getEntity(name);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                R.color.blueLight,
                R.color.btn_cm_bg_pressed,
                R.color.withe);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        addTabLayout();
        addScrollListener();
    }

    private void addScrollListener () {
        if (mOnScrollListener != null) {
            recyclerView.removeOnScrollListener(mOnScrollListener);
        }
        mOnScrollListener = new EndlessRecyclerOnScrollListener((LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int currentPage) {
                isFirst = false;
                if (isLoading) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            current = current + 1 ;
                            getEntity(name);
                        }
                    }, 500);
                }
            }
        };
        recyclerView.addOnScrollListener(mOnScrollListener);
    }

    public void getData(){
        list.add(name);
        for(int i = 0; i <10; i++){
            list.add("TCL产业" + i);
        }
    }

    public void addTabLayout(){
        tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);
        getData();
        /**动态添加值**/
        for (int i = 0; i < list.size(); i++) {
            tabLayout.addTab(tabLayout.newTab());
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(R.layout.tab_layout);
                View view = tab.getCustomView();
                TextView textView = (TextView) view.findViewById(R.id.tv_txt);
                textView.setText(list.get(i));
            }
        }
        /**默认选择第一项itemSelected = 0 **/
        TabLayout.Tab tab = tabLayout.getTabAt(itemSelected);
        tab.select();

        /**计算滑动的偏移量**/
        final int width = (int) (getOffsetWidth(itemSelected) * getResources().getDisplayMetrics().density);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.scrollTo(width, 0);
            }
        });

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                itemSelected =tab.getPosition();
                mSwipeRefreshLayout.setRefreshing(true);
                isFirst = true;
                name = list.get(itemSelected);
                getEntity(name);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    /**根据字符个数计算偏移量*/
    private int getOffsetWidth(int index) {
        String str = "";
        for (int i = 0; i < index; i++) {
            str += list.get(i);
        }
        return str.length() * 14 + index * 12;
    }

    public void getEntity(String name) {
        if(isFirst){
            current = 1;
            records.clear();
            records.addAll( recyclerEntity.parseData(current,name));
            upDownRefreshAdapter= new UpDownRefreshAdapter(this,records);
            recyclerView.setAdapter(upDownRefreshAdapter);
        }else{
            records.addAll(recyclerEntity.parseData(current,name));
            upDownRefreshAdapter.notifyDataSetChanged();
            }
        isLoading = (Integer.valueOf(pages) > Integer.valueOf(current));
        upDownRefreshAdapter.setGone(!isLoading);
        addScrollListener();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        isFirst = true;
        getEntity(name);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
