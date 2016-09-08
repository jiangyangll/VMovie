package com.example.dllo.vmovie;

import cn.bmob.v3.BmobUser;

/**
 * Created by dllo on 16/9/8.
 */
public class UserBean extends BmobUser {
    private String headImageUrl;

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }
}
