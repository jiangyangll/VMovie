package com.example.dllo.vmovie.series;

/**
 * Created by dllo on 16/9/10.
 */
public class SubscribeListBean {
    private String series_postid;
    private String number;
    private String title;
    private String addtime;
    private String duration;
    private String thumbnail;
    private String source_link;

    public String getSeries_postid() {
        return series_postid;
    }

    public void setSeries_postid(String series_postid) {
        this.series_postid = series_postid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSource_link() {
        return source_link;
    }

    public void setSource_link(String source_link) {
        this.source_link = source_link;
    }
}
