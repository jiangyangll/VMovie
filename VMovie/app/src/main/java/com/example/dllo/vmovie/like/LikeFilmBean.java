package com.example.dllo.vmovie.like;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dllo on 16/9/6.
 */
public class LikeFilmBean {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    private String title;
    private String duration;
    private String shareNum;
    private String ratingNum;
    private String imgUrl;
    private String postId;

    public LikeFilmBean() {
    }

    public LikeFilmBean(String title, String duration, String shareNum, String ratingNum, String imgUrl, String postId) {
        this.title = title;
        this.duration = duration;
        this.shareNum = shareNum;
        this.ratingNum = ratingNum;
        this.imgUrl = imgUrl;
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getShareNum() {
        return shareNum;
    }

    public void setShareNum(String shareNum) {
        this.shareNum = shareNum;
    }

    public String getRatingNum() {
        return ratingNum;
    }

    public void setRatingNum(String ratingNum) {
        this.ratingNum = ratingNum;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
