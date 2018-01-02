package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.util;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.BlogAuthor;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.BlogCategory;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.BlogContent;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.BlogIntroduction;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者： 叶应是叶
 * 时间： 2017/3/20 10:44
 * 描述： 用来解析各项网页信息
 */
public class JsoupUtil {

    /**
     * 博客网站首页
     */
    private static final String BASE_PATH = "http://blog.csdn.net/";

    /**
     * 博主主页
     */
    private static final String BLOG_HOMEPAGE = BASE_PATH + Constants.BLOG_AUTHOR_NAME;

    /**
     * 获取博主的基本信息
     *
     * @return 博主信息
     */
    public static BlogAuthor getBlogAutoMessage() {
        Document doc;
        BlogAuthor blogAuthor = null;
        Elements elements;
        /*
         * 作者名字
         */
        String authorName;
        /*
         * 访问数量
         */
        String visitNumber;
        /*
         * 积分
         */
        String mark;
        /*
         * 排名
         */
        String rank;
        /*
         * 原创文章数量
         */
        String originalArticleNumber;
        /*
         * 转载文章数量
         */
        String reprintArticleNumber;
        /*
         * 翻译文章数量
         */
        String translateArticleNumber;
        /*
         * 评论数量
         */
        String commentNumber;
        /*
         * 头像链接
         */
        String avatarUrl;

        try {
            doc = Jsoup.connect(BLOG_HOMEPAGE)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
                    .timeout(10000).get();
            if (doc != null) {
                elements = doc.select("div#blog_userface").select("a.user_name");
                authorName = elements.first().text();
                elements = doc.select("div#blog_userface").select("a").select("img");
                avatarUrl = elements.first().attr("src");
                elements = doc.select("ul#blog_rank").select("li");
                visitNumber = elements.get(0).text();
                mark = elements.get(1).text();
                rank = elements.get(3).text();
                elements = doc.select("ul#blog_statistics").select("li");
                originalArticleNumber = elements.get(0).text();
                reprintArticleNumber = elements.get(1).text();
                translateArticleNumber = elements.get(2).text();
                commentNumber = elements.get(3).text();
                blogAuthor = new BlogAuthor(authorName, visitNumber, mark, rank, originalArticleNumber, reprintArticleNumber, translateArticleNumber, commentNumber, avatarUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
            blogAuthor = new BlogAuthor("叶应是叶", "访问：0", "积分：0", "积分：0", "原创：0", "转载：0", "译文：0", "评论：0", "");
        }
        return blogAuthor;
    }

    /**
     * 获取博主所有博客分类信息，包括分类目录，目录下文章数量，该分类的链接
     *
     * @return 分类信息
     */
    private static List<BlogCategory> getBlogAllCategory() {
        Document doc;
        Elements elements = null;
        List<BlogCategory> blogCategoryList = null;
        BlogCategory blogCategory;
        try {
            doc = Jsoup.connect(BLOG_HOMEPAGE)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
                    .timeout(10000).get();
            if (doc != null) {
                elements = doc.select("div#panel_Category").select("ul.panel_body > li");
            }
            if (elements != null && !"".equals(elements.toString())) {
                blogCategoryList = new ArrayList<>();
                String category;
                int number;
                String link;
                String numberStr;
                int numberStrLength;
                for (Element element : elements) {
                    blogCategory = new BlogCategory();
                    category = element.select("a").text();
                    numberStr = element.select("span").text();
                    numberStrLength = numberStr.length();
                    number = Integer.valueOf(numberStr.substring(1, numberStrLength - 1));
                    link = element.select("a").attr("href");
                    blogCategory.setCategory(category);
                    blogCategory.setNumber(number);
                    blogCategory.setLink(BASE_PATH + link);
                    blogCategoryList.add(blogCategory);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return blogCategoryList;
    }

    /**
     * 获取分类的链接后缀--例如，“Android”的链接后缀为“category/6094279”
     *
     * @param category 文章分类
     * @return 后缀
     */
    private static String getCodeByCategory(String category) {
        List<BlogCategory> bgList = getBlogAllCategory();
        if (bgList != null) {
            for (BlogCategory blogCategory : bgList) {
                if (blogCategory.getCategory().equals(category)) {
                    String link = blogCategory.getLink();
                    int beginIndex = link.indexOf("category/");
                    return link.substring(beginIndex);
                }
            }
        }
        return "";
    }

    /**
     * 获取指定分类下目录页数
     *
     * @param category 分类
     * @return 页数
     */
    private static int getCategoryPages(String category) {
        String code = getCodeByCategory(category);
        if (code.length() == 0) {
            return 0;
        }
        String suffix;
        Document doc;
        Elements pageList;
        String pages = "1";
        suffix = "/article/" + code;
        try {
            doc = Jsoup.connect(BLOG_HOMEPAGE + suffix)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
                    .timeout(10000).get();
            if (doc != null) {
                pageList = doc.select("div#papelist").select("span");
                if (pageList != null && !"".equals(pageList.text())) {
                    pages = pageList.text();
                    // 文章页数
                    pages = pages.substring(pages.indexOf("共") + 1, pages.indexOf("页"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Integer.valueOf(pages);
    }

    /**
     * 获取指定分类下指定页的博客简介
     *
     * @param category 分类
     * @param pages    页数
     * @return 简介
     */
    public static List<BlogIntroduction> getOnePageBlogIntroductionByCategory(String category, int pages) {
        if (pages < 1) {
            return null;
        }
        if (pages > getCategoryPages(category)) {
            return null;
        }
        String code = getCodeByCategory(category);
        Document doc;
        Elements blogList;
        String suffix = "/article/" + code + "/" + pages;
        List<BlogIntroduction> blogIntroductionList = null;
        BlogIntroduction blogIntroduction;
        try {
            doc = Jsoup.connect(BLOG_HOMEPAGE + suffix)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
                    .timeout(10000).get();
            blogList = doc.select("div#article_list > div");
            if (blogList != null) {
                blogIntroductionList = new ArrayList<>();
                for (Element blogItem : blogList) {
                    blogIntroduction = new BlogIntroduction();
                    String title = blogItem.select("div.article_title > h1").text();
                    String description = blogItem.select("div.article_description").text();
                    String msg = blogItem.select("div.article_manage").text();
                    String link = BASE_PATH + blogItem.select("div.article_title > h1").select("span.link_title")
                            .select("a").attr("href");
                    blogIntroduction.setTitle(title);
                    blogIntroduction.setDescription(description);
                    blogIntroduction.setMsg(msg);
                    blogIntroduction.setUrl(link);
                    blogIntroduction.setCategory(category);
                    blogIntroductionList.add(blogIntroduction);
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return blogIntroductionList;
    }

    /**
     * 按时间排列，获取指定页的博客简介
     *
     * @param pages 页数
     * @return 简介
     */
    public static List<BlogIntroduction> getOnePageBlogIntroductionByTime(int pages) {
        if (pages < 1) {
            return null;
        }
        int totalPages = getBlogPages();
        if (pages > totalPages) {
            return null;
        }
        Document doc;
        Elements blogList;
        List<BlogIntroduction> blogIntroductionList = null;
        BlogIntroduction blogIntroduction;
        try {
            doc = Jsoup.connect(BLOG_HOMEPAGE + "/article/list/" + pages)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
                    .timeout(10000).get();
            blogList = doc.select("div#article_list > div");
            if (blogList != null) {
                blogIntroductionList = new ArrayList<>();
                for (Element blogItem : blogList) {
                    blogIntroduction = new BlogIntroduction();
                    String title = blogItem.select("div.article_title > h1").text();
                    String description = blogItem.select("div.article_description").text();
                    String msg = blogItem.select("div.article_manage").text();
                    String link = BASE_PATH + blogItem.select("div.article_title > h1").select("span.link_title")
                            .select("a").attr("href");
                    blogIntroduction.setTitle(title);
                    blogIntroduction.setDescription(description);
                    blogIntroduction.setMsg(msg);
                    blogIntroduction.setUrl(link);
                    blogIntroduction.setCategory("");
                    blogIntroductionList.add(blogIntroduction);
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return blogIntroductionList;
    }

    /**
     * 获取所有文章的页数
     *
     * @return 页数
     */
    private static int getBlogPages() {
        Document doc;
        Elements pageList;
        String pages = "1";
        try {
            doc = Jsoup.connect(BLOG_HOMEPAGE)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
                    .timeout(10000).get();
            if (doc != null) {
                pageList = doc.select("div#papelist").select("span");
                if (pageList != null && !"".equals(pageList.text())) {
                    pages = pageList.text();
                    // 文章页数
                    pages = pages.substring(pages.indexOf("共") + 1, pages.indexOf("页"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            pages = "0";
        }
        return Integer.valueOf(pages);
    }

    /**
     * 获取博主文章简介-----如果是要获取所有文章，suffix="list/"
     * 如果是要获取某个分类的文章，suffix="category/"+一串数字
     *
     * @param suffix 链接的后缀
     * @return 内容
     */
    private static List<BlogIntroduction> getBlogIntroduction(String suffix) {
        Document doc;
        Elements pageList;
        Elements blogList;
        String nextUrl;
        suffix = "/article/" + suffix;
        String pages = "1";
        List<BlogIntroduction> blogIntroductionList = null;
        BlogIntroduction blogIntroduction;
        try {
            doc = Jsoup.connect(BLOG_HOMEPAGE + suffix)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
                    .timeout(10000).get();
            blogIntroductionList = new ArrayList<>();
            if (doc != null) {
                pageList = doc.select("div#papelist").select("span");
                if (pageList != null && !"".equals(pageList.text())) {
                    pages = pageList.text();
                    // 文章页数
                    pages = pages.substring(pages.indexOf("共") + 1, pages.indexOf("页"));
                }
                for (int i = 1; i <= Integer.valueOf(pages); i++) {
                    if (i != 1) {
                        nextUrl = BLOG_HOMEPAGE + suffix + "/" + i;
                        doc = Jsoup.connect(nextUrl)
                                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
                                .timeout(10000).get();
                    }
                    blogList = doc.select("div#article_list > div");
                    if (blogList != null) {
                        for (Element blogItem : blogList) {
                            blogIntroduction = new BlogIntroduction();
                            String title = blogItem.select("div.article_title > h1").text();
                            String description = blogItem.select("div.article_description").text();
                            String msg = blogItem.select("div.article_manage").text();
                            String link = BASE_PATH + blogItem.select("div.article_title > h1")
                                    .select("span.link_title").select("a").attr("href");
                            blogIntroduction.setTitle(title);
                            blogIntroduction.setDescription(description);
                            blogIntroduction.setMsg(msg);
                            blogIntroduction.setUrl(link);
                            blogIntroduction.setCategory("");
                            blogIntroductionList.add(blogIntroduction);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return blogIntroductionList;
    }

    /**
     * 根据链接获取博客内容
     *
     * @param blogUrl url
     * @return 博客内容
     */
    public static List<BlogContent> getBlogContent(String blogUrl) {
        Document doc = null;
        Element element = null;
        try {
            doc = Jsoup.connect(blogUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
                    .timeout(10000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc != null) {
            // 获取标题区域
            element = doc.select("div#article_details > div").first();
        }
        if (element == null) {
            return null;
        }
        // 标题
        String title = element.text();
        // 获取总的内容区域下的所有子元素
        Elements subElements = doc.select("div#article_content > div > *");
        List<BlogContent> blogContents = new ArrayList<>();

        BlogContent blogContent = new BlogContent();
        blogContent.setType(BlogContent.enumContentType.BLOG_TITLE);
        blogContent.setContent(title);

        blogContents.add(blogContent);

        Elements imageElements;
        for (Element e : subElements) {
            blogContent = new BlogContent();
            if (isRightType(e.tagName())) {
                String str = e.toString().replace("<br />", "#换行#").replace("<br>", "#换行#");
                doc = Jsoup.parse(str);
                Element elem = doc.select(e.tagName()).first();
                blogContent.setType(BlogContent.enumContentType.BLOG_TEXT);
                blogContent.setContent(elem.text().replace("#换行#", "\n").replace("\n\n", "\n").trim());
                blogContents.add(blogContent);
                imageElements = e.select("img");
                if (imageElements != null) {
                    for (Element el : imageElements) {
                        blogContent = new BlogContent();
                        blogContent.setType(BlogContent.enumContentType.BLOG_IMAGE);
                        blogContent.setContent(el.attr("src"));
                        blogContents.add(blogContent);
                    }
                }
            } else if (e.tagName().equals("pre")) {
                blogContent.setType(BlogContent.enumContentType.BLOG_CODE);
                blogContent.setContent(e.text().replace("\n\n", "\n"));
                blogContents.add(blogContent);
            }
        }
        return blogContents;
    }

    private static boolean isRightType(String type) {
        return (type.equals("p") || type.equals("h1") || type.equals("h2") || type.equals("h3") || type.equals("h4")
                || type.equals("h5") || type.equals("h6") || type.equals("ul") || type.equals("ol") || type.equals("center"));
    }

}
