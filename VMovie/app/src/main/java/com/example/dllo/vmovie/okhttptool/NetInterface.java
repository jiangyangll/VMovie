package com.example.dllo.vmovie.okhttptool;

/**
 * Created by dllo on 16/8/30.
 */
public interface NetInterface {

    void startRequest(String url,OnHttpCallBack<String> callBack);
    <T> void startRequest(String url,Class<T> tClass,OnHttpCallBack<T> callBack);
}
