package com.windbridget.w_videoplayer;

import java.io.Serializable;

/**
 * Created by Ru on 11/18/2016.
 */

public class Video implements Serializable{
    public String videoId;
    public String title;
    public String url;

    public Video(String videoId, String title, String url) {
        this.title = title;
        this.videoId = videoId;
        this.url = url;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
