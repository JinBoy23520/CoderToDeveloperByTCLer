package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页Banner
 * Created by 坚果-王健(wangjian3@kuyumall.com) on 2016/11/29.
 */

public class BannerEntity extends AbsBaseEntity {

    private List<BannerItem> mBannerItems = new ArrayList<>();

    public List<BannerItem> getmBannerItems() {
        return mBannerItems;
    }

    public void setmBannerItems(List<BannerItem> mBannerItems) {
        this.mBannerItems = mBannerItems;
    }

    public List<BannerItem> getBannerItems() {
        return mBannerItems;
    }


    @Override
    public void parseData() {
        BannerEntity bannerEntity = new BannerEntity();
        BannerItem bannerItem1 = new BannerItem();
        bannerItem1.setImageUrl("http://img.my.csdn.net/uploads/201706/05/1496633391_3312.png-thumb.jpg");
        bannerItem1.setWebUrl("http://blog.csdn.net/dt235201314");
        mBannerItems.add(bannerItem1);
        BannerItem bannerItem2 = new BannerItem();
        bannerItem2.setImageUrl("http://img.my.csdn.net/uploads/201706/05/1496633360_6095.png-thumb.jpg");
        bannerItem2.setWebUrl("http://www.jianshu.com/u/905c7de5ae83");
        mBannerItems.add(bannerItem2);
        BannerItem bannerItem3 = new BannerItem();
        bannerItem3.setImageUrl("http://img.my.csdn.net/uploads/201706/02/1496381926_5428.png-thumb.jpg");
        bannerItem3.setWebUrl("https://github.com/JinBoy23520");
        mBannerItems.add(bannerItem3);
        bannerEntity.setmBannerItems(mBannerItems);
    }

    public class BannerItem implements Serializable {
        private String id;
        private String webUrl;
        private String imageUrl;
        private String title;
        private String descrption;

//        public void parseFrmoJson(JSONObject jsonObject) {
//            id = jsonObject.optString("bnId");
//            title = jsonObject.optString("bnName");
//            descrption = jsonObject.optString("bnDesc");
//            imageUrl = jsonObject.optString("bnImage");
//            webUrl = jsonObject.optString("bnUrl");
//
//            JSONObject activityRelatedOjb = jsonObject.optJSONObject("activityRelated");
//            if(activityRelatedOjb!=null){
//                activityRelated = new ActivityRelated();
//                activityRelated.actId = activityRelatedOjb.optLong("actId");
//                activityRelated.actName = activityRelatedOjb.optString("actName");
//            }
//        }


        public String getWebUrl() {
            return webUrl;
        }

        public void setWebUrl(String webUrl) {
            this.webUrl = webUrl;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDescrption() {
            return descrption;
        }

        public void setDescrption(String descrption) {
            this.descrption = descrption;
        }
    }

}
