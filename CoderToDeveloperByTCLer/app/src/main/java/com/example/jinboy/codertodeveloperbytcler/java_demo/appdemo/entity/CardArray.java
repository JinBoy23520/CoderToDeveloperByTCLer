package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2018/08/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class CardArray extends AbsBaseEntity{
    private List<CardItem> mCardItems;

    public List<CardItem> getmCardItems() {
        return mCardItems;
    }

    public void setmCardItems(List<CardItem> mCardItems) {
        this.mCardItems = mCardItems;
    }

    @Override
    public void parseData() {
        mCardItems = new ArrayList<>();
        CardItem cardItem1 = new CardItem();
        cardItem1.setImagUrl("http://www.xuetangx.com/asset-v1:AFEU+20180116001+2018_T1+type@asset+block@11.jpg");
        cardItem1.setWebUrl("http://www.xuetangx.com/courses/course-v1:AFEU+20180116001+2018_T1/about");
        cardItem1.setPeopleNum("20000+");
        cardItem1.setTitle("航空电机学");
        mCardItems.add(cardItem1);

        CardItem cardItem2 = new CardItem();
        cardItem2.setImagUrl("http://www.xuetangx.com/asset-v1:AFEU+20180131001+2018_T2+type@asset+block@%E8%AF%BE%E7%A8%8B%E9%A6%96%E9%A1%B5.jpg");
        cardItem2.setWebUrl("http://www.xuetangx.com/courses/course-v1:AFEU+20180131001+2018_T2/about");
        cardItem2.setPeopleNum("20000+");
        cardItem2.setTitle("飞机推进系统原理");
        mCardItems.add(cardItem2);

        CardItem cardItem3 = new CardItem();
        cardItem3.setImagUrl("http://www.xuetangx.com/asset-v1:SEU+20171205001+sp+type@asset+block@%E5%86%9B%E4%BA%8B.jpg");
        cardItem3.setWebUrl("http://www.xuetangx.com/courses/course-v1:SEU+20171205001+2018_T1/about");
        cardItem3.setPeopleNum("20000+");
        cardItem3.setTitle("军事理论");
        mCardItems.add(cardItem3);

        CardItem cardItem4 = new CardItem();
        cardItem4.setImagUrl("http://img5.cache.netease.com/video/2014/7/15/2014071515340993d92.jpg");
        cardItem4.setWebUrl("http://open.163.com/special/opencourse/aspacetimeodyssey.html");
        cardItem4.setPeopleNum("20000+");
        cardItem4.setTitle("宇宙时空之旅");
        mCardItems.add(cardItem4);

    }
}
