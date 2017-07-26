package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        getEntity();
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                R.color.blueLight,
                R.color.btn_cm_bg_pressed,
                R.color.withe);
        mSwipeRefreshLayout.setOnRefreshListener(this);
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
                            getEntity();
                        }
                    }, 500);

                }
            }
        };
        recyclerView.addOnScrollListener(mOnScrollListener);
    }

    public void getEntity() {
        if(isFirst){
            current = 1;
            records.clear();
            records.addAll( recyclerEntity.parseData(current,"全部"));
            upDownRefreshAdapter= new UpDownRefreshAdapter(this,records);
            recyclerView.setAdapter(upDownRefreshAdapter);
        }else{
            records.addAll(recyclerEntity.parseData(current,"全部"));
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
        getEntity();
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
