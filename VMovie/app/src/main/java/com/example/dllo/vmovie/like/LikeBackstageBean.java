package com.example.dllo.vmovie.like;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dllo on 16/9/6.
 */
public class LikeBackstageBean {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    private String postId;
    private String title;
    private String imageUrl;
    private String shareNum;
    private String likeNum;

    public LikeBackstageBean() {
    }

    public LikeBackstageBean(String postId, String title, String imageUrl, String shareNum, String likeNum) {
        this.postId = postId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.shareNum = shareNum;
        this.likeNum = likeNum;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getShareNum() {
        return shareNum;
    }

    public void setShareNum(String shareNum) {
        this.shareNum = shareNum;
    }

    public String getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(String likeNum) {
        this.likeNum = likeNum;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
