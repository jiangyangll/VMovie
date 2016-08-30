package com.example.dllo.vmovie.okhttptool;

/**
 * Created by dllo on 16/8/30.
 */
public interface OnHttpCallBack<T> {

    void onSuccess(T response);
    void onError(Throwable e);
}
