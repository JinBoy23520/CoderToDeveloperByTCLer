package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity;

/**
 * 作者： 叶应是叶
 * 时间： 2017/3/20 10:30
 * 描述： 博客文章的分类
 */
public class BlogCategory {

    //文章的分类
    private String category;

    //该分类下文章的数量
    private int number;

    //该分类链接
    private String link;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
