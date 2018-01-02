package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity;

/**
 * 作者： 叶应是叶
 * 时间： 2017/3/20 10:30
 * 描述： 博主信息
 */
public class BlogAuthor {

    //作者名字
    private String authorName;

    //访问数量
    private String visitNumber;

    //积分
    private String mark;

    //排名
    private String rank;

    //原创文章数量
    private String originalArticleNumber;

    //转载文章数量
    private String reprintArticleNumber;

    //翻译文章数量
    private String translateArticleNumber;

    //评论数量
    private String commentNumber;

    //头像链接
    private String avatarUrl;

    public BlogAuthor(String authorName, String visitNumber, String mark, String rank, String originalArticleNumber, String reprintArticleNumber, String translateArticleNumber, String commentNumber, String avatarUrl) {
        this.authorName = authorName;
        this.visitNumber = visitNumber;
        this.mark = mark;
        this.rank = rank;
        this.originalArticleNumber = originalArticleNumber;
        this.reprintArticleNumber = reprintArticleNumber;
        this.translateArticleNumber = translateArticleNumber;
        this.commentNumber = commentNumber;
        this.avatarUrl = avatarUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(String visitNumber) {
        this.visitNumber = visitNumber;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getOriginalArticleNumber() {
        return originalArticleNumber;
    }

    public void setOriginalArticleNumber(String originalArticleNumber) {
        this.originalArticleNumber = originalArticleNumber;
    }

    public String getReprintArticleNumber() {
        return reprintArticleNumber;
    }

    public void setReprintArticleNumber(String reprintArticleNumber) {
        this.reprintArticleNumber = reprintArticleNumber;
    }

    public String getTranslateArticleNumber() {
        return translateArticleNumber;
    }

    public void setTranslateArticleNumber(String translateArticleNumber) {
        this.translateArticleNumber = translateArticleNumber;
    }

    public String getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(String commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

}
