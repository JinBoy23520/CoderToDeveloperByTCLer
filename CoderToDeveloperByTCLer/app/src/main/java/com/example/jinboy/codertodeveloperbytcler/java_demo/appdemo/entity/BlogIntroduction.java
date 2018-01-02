package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity;

/**
 * 作者： 叶应是叶
 * 时间： 2017/3/20 10:40
 * 描述：博客文章的简要信息
 */
public class BlogIntroduction {

    //文章标题
    private String title;

    //文章摘要
    private String description;

    //文章信息，包括阅读量，评论数，发表时间等
    private String msg;

    //文章分类
    private String category;

    //文章链接
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
