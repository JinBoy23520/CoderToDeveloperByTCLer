package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.WebViewActivity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.view.MyOneLineView;


/**
 * 我的页面，圆形图片+封装通用item
 */
public class MineFragment extends Fragment implements MyOneLineView.OnRootClickListener, MyOneLineView.OnArrowClickListener {
    LinearLayout ll_mine_item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.mine_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    public void initView(){
        ll_mine_item = (LinearLayout) getActivity().findViewById(R.id.ll_mine_item);
        //使用示例，通过Java代码来创建MyOnelineView
        //icon + 文字 + 箭头
        ll_mine_item.addView(new MyOneLineView(getActivity())
                .initMine(R.mipmap.mine_about_icon, "我的CSDN博客", "技术博文", true)
                .setOnRootClickListener(this, 1));

        ll_mine_item.addView(new MyOneLineView(getActivity())
                .initMine(R.mipmap.mine_about_icon, "我的简书", "技术问 + 感想", true)
                .setOnRootClickListener(this, 2));

        ll_mine_item.addView(new MyOneLineView(getActivity())
                .initMine(R.mipmap.mine_about_icon, "我的GitHub", "", true)
                .setOnRootClickListener(this, 3));

        ll_mine_item.addView(new MyOneLineView(getActivity())
                .initMine(R.mipmap.mine_about_icon, "关于APP", "规划中", true)
                .setDividerTopColor(R.color.gray2)
                .showDivider(true,true)
                .setDividerTopHigiht(10)
                .setOnRootClickListener(this, 4));

        ll_mine_item.addView(new MyOneLineView(getActivity())
                .initMine(R.mipmap.mine_version_update_icon, "版本更新", "规划中", true)
                .setOnRootClickListener(this, 5));

        ll_mine_item.addView(new MyOneLineView(getActivity())
                .initMine(R.mipmap.mine_account_setting_icon, "账户设置", "规划中", true)
                .setOnRootClickListener(this, 6));
//        //icon + 文字 + 文字 + 箭头
//        ll_mine_item.addView(new MyOneLineView(getActivity())
//                .initMine(R.mipmap.ic_launcher, "第二行", "第二行", true)
//                .setOnArrowClickListener(this, 2));
//        //icon + 文字 + 输入框
//        ll_mine_item.addView(new MyOneLineView(getActivity())
//                .initItemWidthEdit(R.mipmap.ic_launcher, "第三行", "这是一个输入框")
//                .setRootPaddingTopBottom(20, 20));
    }

    @Override
    public void onRootClick(View view) {
        switch ((int) view.getTag()) {
            case 1:
                WebViewActivity.startWebViewActivity(getActivity(),"http://my.csdn.net/dt235201314");
                break;
            case 2:
                WebViewActivity.startWebViewActivity(getActivity(),"http://www.jianshu.com/u/905c7de5ae83");
                break;
            case 3:
                WebViewActivity.startWebViewActivity(getActivity(),"https://github.com/JinBoy23520");
                break;
            case 4:
                Toast.makeText(getActivity(), "规划中", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(getActivity(), "规划中", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Toast.makeText(getActivity(), "规划中", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onArrowClick(View view) {

    }
}
