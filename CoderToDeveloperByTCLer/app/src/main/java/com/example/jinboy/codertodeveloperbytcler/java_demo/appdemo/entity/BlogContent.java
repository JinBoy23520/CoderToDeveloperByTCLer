package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity;

/**
 * 作者： 叶应是叶
 * 时间： 2017/3/20 10:39
 * 描述： 博客内容，分为标题、正文、代码、图像四类
 */
public class BlogContent {

    public enum enumContentType {
        BLOG_TITLE, BLOG_TEXT, BLOG_CODE, BLOG_IMAGE
    }

    //内容分类
    private enumContentType type;

    //具体内容
    private String content;

    public enumContentType getType() {
        return type;
    }

    public void setType(enumContentType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
