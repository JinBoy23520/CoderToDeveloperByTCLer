package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.CardArray;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.adapter.CardPagerAdapter;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.view.ShadowTransformer;


public class ViewPagerCardsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_cards_activity);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        CardArray cardArray = new CardArray();
        cardArray.parseData();
        mCardAdapter = new CardPagerAdapter(this,cardArray.getmCardItems());
        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
        mCardShadowTransformer.enableScaling(true);
    }

    @Override
    public void onClick(View v) {

    }

}
