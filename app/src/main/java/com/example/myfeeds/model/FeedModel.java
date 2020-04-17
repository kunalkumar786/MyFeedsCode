package com.example.myfeeds.model;

import com.google.gson.annotations.SerializedName;

public class FeedModel {

    private String header_title;

    public FeedModel() {
    }

    public FeedModel(String imageHref, String title, String description) {
        this.imageHref=imageHref;
        this.title=title;
        this.description=description;
    }

    public String getHeader_title() {
        return header_title;
    }

    public void setHeader_title(String header_title) {
        this.header_title = header_title;
    }

    private String title;

    private String description;

    private String imageHref;
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

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }


    public void setItem(FeedModel item) {
    }
}
