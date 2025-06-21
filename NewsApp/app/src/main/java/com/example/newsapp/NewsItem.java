package com.example.newsapp;

public class NewsItem {

    private String imageResUrl;
    private String headline;
    private String article;

    public NewsItem(String imageResUrl, String headline, String article) {
        this.imageResUrl = imageResUrl;
        this.headline = headline;
        this.article = article;
    }

    public String getImageResUrl() {
        return imageResUrl;
    }

    public void setImageResId(String imageResUrl) {
        this.imageResUrl = imageResUrl;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }
}
