package com.gpjypt.dome1.bean;

public class NewsItem {
    String title;
    String date;
    String author_name;
    String url;

    public NewsItem(String title, String date, String author_name, String url) {
        this.title = title;
        this.date = date;
        this.author_name = author_name;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public String getUrl() {
        return url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
