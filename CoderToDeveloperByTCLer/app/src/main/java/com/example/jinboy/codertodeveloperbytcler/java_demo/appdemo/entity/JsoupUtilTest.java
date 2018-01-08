package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity;


import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.util.JsoupUtil;

import java.util.List;

/**
 * <pre>
 *     author : JinBiao-
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2018/01/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class JsoupUtilTest {
    public static void main(String[] args) {
        BlogAuthor blogAuthor = JsoupUtil.getBlogAutoMessage();
        List<BlogIntroduction> blogs = JsoupUtil.getOnePageBlogIntroductionByTime(1);
        System.out.println("==-->"+ blogAuthor.getAuthorName());
        System.out.println("==-->"+blogAuthor.getCommentNumber());
        System.out.println("==-->"+ blogAuthor.getAvatarUrl());
        System.out.println("==-->"+ blogAuthor.getVisitNumber());
        System.out.println("==-->"+blogAuthor.getRank());
        System.out.println("==-->"+blogAuthor.getMark());
        System.out.println("==-->"+blogAuthor.getOriginalArticleNumber());
        System.out.println("==-->"+blogAuthor.getReprintArticleNumber());
        System.out.println("==-->"+blogAuthor.getTranslateArticleNumber());
        System.out.println("==-->"+blogAuthor.getCode());
        System.out.println("==-->"+blogAuthor.getMyHelloWorld());
        for(BlogIntroduction blog:blogs){
            System.out.println("==-->"+blog.getTitle());
            System.out.println("==-->"+blog.getDescription());
        }
    }
}
