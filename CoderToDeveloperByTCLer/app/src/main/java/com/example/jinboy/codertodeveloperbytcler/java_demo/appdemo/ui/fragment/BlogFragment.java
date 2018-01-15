package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.fragment;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.BlogIntroduction;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.Common.CommonViewHolder;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.adapter.BlogListAdapter;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.adapter.PagerAdapter;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.util.JsoupUtil;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class BlogFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, CommonViewHolder.onItemCommonClickListener {

    private View view;

    private SwipeRefreshLayout swipeRefreshLayout;

    public List<BlogIntroduction> blogIntroductionList;

    private BlogListAdapter blogListAdapter;

    //标记当前要加载的博客文章类型
    private int blogType;

    //用来防止连续多次的网络请求
    private boolean isGetting;

    //标记当前加载到的文章页数
    private int currentLoadPage;

    private static final String BLOG_TYPE = "blogType";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.blogType = getArguments().getInt(BLOG_TYPE);
    }

    public static BlogFragment getBlogFragment(int blogType) {
        BlogFragment blogFragment = new BlogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BLOG_TYPE, blogType);
        blogFragment.setArguments(bundle);
        return blogFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_blog, container, false);
            swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
            swipeRefreshLayout.setOnRefreshListener(this);
            RecyclerView rv_blogList = (RecyclerView) view.findViewById(R.id.rv_blogList);
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            rv_blogList.setLayoutManager(linearLayoutManager);
            blogIntroductionList = new ArrayList<>();
            blogListAdapter = new BlogListAdapter(getContext(), blogIntroductionList, R.layout.item_blog, this);
            rv_blogList.setAdapter(blogListAdapter);
            swipeRefreshLayout.setRefreshing(true);
            rv_blogList.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    if (linearLayoutManager.findLastVisibleItemPosition() == blogIntroductionList.size() - 1) {
                        new LoadMoreDataTask().execute(blogType);
                    }
                }
            });
            new RefreshDataTask().execute(blogType);
        }
        return view;
    }

    @Override
    public void onRefresh() {
        new RefreshDataTask().execute(blogType);
    }

    @Override
    public void onItemClickListener(int position) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Settings", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("useWeb", false)) {
//            ContentByWebActivity.navToContentByWebActivity(getActivity(), blogIntroductionList.get(position).getUrl());
        } else {
//            ContentByJsoupActivity.navToContentByJsoupActivity(getActivity(), blogIntroductionList.get(position).getUrl());
        }
    }

    @Override
    public void onItemLongClickListener(int position) {

    }

    private class RefreshDataTask extends AsyncTask<Integer, Void, List<BlogIntroduction>> {
        @Override
        protected List<BlogIntroduction> doInBackground(Integer... params) {
            isGetting = true;
            List<BlogIntroduction> blogIntroductionList;
            if (params[0] == 0) {
                blogIntroductionList = JsoupUtil.getOnePageBlogIntroductionByTime(1);
            } else {
                blogIntroductionList = JsoupUtil.getOnePageBlogIntroductionByCategory(PagerAdapter.TAGS[params[0]], 1);
            }
            return blogIntroductionList;
        }

        @Override
        protected void onPostExecute(List<BlogIntroduction> tempBlogIntroductionList) {
            if (tempBlogIntroductionList != null && tempBlogIntroductionList.size() > 0) {
                blogIntroductionList.clear();
                blogIntroductionList.addAll(tempBlogIntroductionList);
                currentLoadPage = 1;
                blogListAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), "数据获取不到啊~", Toast.LENGTH_SHORT).show();
            }
            swipeRefreshLayout.setRefreshing(false);
            isGetting = false;
        }
    }

    private class LoadMoreDataTask extends AsyncTask<Integer, Void, List<BlogIntroduction>> {
        @Override
        protected List<BlogIntroduction> doInBackground(Integer... params) {
            //如果此时没有在进行获取数据的操作的话
            if (!isGetting) {
                isGetting = true;
                if (params[0] == 0) {
                    List<BlogIntroduction> list = JsoupUtil.getOnePageBlogIntroductionByTime(currentLoadPage + 1);
                    if (list != null && list.size() > 0) {
                        return list;
                    }
                } else {
                    List<BlogIntroduction> list = JsoupUtil.getOnePageBlogIntroductionByCategory(PagerAdapter.TAGS[params[0]], currentLoadPage + 1);
                    if (list != null && list.size() > 0) {
                        return list;
                    }
                }
                return null;
            }
            // 已经在进行获取数据的操作的话则直接返回
            return new ArrayList<>();
        }

        @Override
        protected void onPostExecute(List<BlogIntroduction> tempBlogIntroductionList) {
            if (tempBlogIntroductionList == null) {
                isGetting = false;
                return;
            }
            if (tempBlogIntroductionList.size() == 0) {
                return;
            }
            blogIntroductionList.addAll(tempBlogIntroductionList);
            blogListAdapter.notifyDataSetChanged();
            currentLoadPage++;
            isGetting = false;
        }
    }

}