package com.example.demo.SpringBoot.entity;

public class Topic {

    private Integer id;
    private String title;
    private String subtitle;
    private String scene_pic_url;
    private Integer price_info;

    public Topic(Integer id, String title, String subtitle, String scene_pic_url, Integer price_info) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.scene_pic_url = scene_pic_url;
        this.price_info = price_info;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getScene_pic_url() {
        return scene_pic_url;
    }

    public void setScene_pic_url(String scene_pic_url) {
        this.scene_pic_url = scene_pic_url;
    }

    public Integer getPrice_info() {
        return price_info;
    }

    public void setPrice_info(Integer price_info) {
        this.price_info = price_info;
    }
}