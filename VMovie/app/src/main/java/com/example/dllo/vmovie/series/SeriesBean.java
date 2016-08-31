package com.example.dllo.vmovie.series;

import java.util.List;

/**
 * Created by dllo on 16/8/30.
 */
public class SeriesBean {

    private String status;
    private String msg;

    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String seriesid;
        private String title;
        private String image;
        private String weekly;
        private String content;
        private String app_image;
        private String isfollow;
        private String is_end;
        private String update_to;
        private String follower_num;

        public String getSeriesid() {
            return seriesid;
        }

        public void setSeriesid(String seriesid) {
            this.seriesid = seriesid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getWeekly() {
            return weekly;
        }

        public void setWeekly(String weekly) {
            this.weekly = weekly;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getApp_image() {
            return app_image;
        }

        public void setApp_image(String app_image) {
            this.app_image = app_image;
        }

        public String getIsfollow() {
            return isfollow;
        }

        public void setIsfollow(String isfollow) {
            this.isfollow = isfollow;
        }

        public String getIs_end() {
            return is_end;
        }

        public void setIs_end(String is_end) {
            this.is_end = is_end;
        }

        public String getUpdate_to() {
            return update_to;
        }

        public void setUpdate_to(String update_to) {
            this.update_to = update_to;
        }

        public String getFollower_num() {
            return follower_num;
        }

        public void setFollower_num(String follower_num) {
            this.follower_num = follower_num;
        }
    }
}
