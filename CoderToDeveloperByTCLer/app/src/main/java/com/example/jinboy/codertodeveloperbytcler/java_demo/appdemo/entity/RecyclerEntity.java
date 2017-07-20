package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/07/07
 *     desc   : RecyclerView返回数据实体类
 *     version: 1.0
 * </pre>
 */

public class RecyclerEntity extends AbsBaseEntity {

    private String category; //品类
    private int pages; //页数
    private int sales; //销量
    private int allPage = 5;



    @Override
    public void parseData() {

    }

    public List<RecyclerEntity> parseData(int pages , String category ) {
        List<RecyclerEntity>  list = new ArrayList<>();
        if(pages == 1){
            for(int i= 0;i<=19;i++){
                RecyclerEntity recyclerEntity = new RecyclerEntity();
                Random r = new Random();
                recyclerEntity.setCategory(category);
                recyclerEntity.setPages(pages);
                recyclerEntity.setSales(r.nextInt(100));
                list.add(recyclerEntity);
            }
        }else {
            for(int i= 0;i<=9;i++){
                RecyclerEntity recyclerEntity = new RecyclerEntity();
                Random r = new Random();
                recyclerEntity.setCategory(category);
                recyclerEntity.setPages(pages);
                recyclerEntity.setSales(r.nextInt(100));
                list.add(recyclerEntity);
            }
        }
        return list;
    }

    @Override
    public String toString() {
        return "RecyclerEntity{" +
                "category='" + category + '\'' +
                ", pages=" + pages +
                ", sales=" + sales +
                '}';
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getAllPage() {
        return allPage;
    }

    public void setAllPage(int allPage) {
        this.allPage = allPage;
    }

}
