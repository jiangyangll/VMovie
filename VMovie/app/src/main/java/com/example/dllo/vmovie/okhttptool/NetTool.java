package com.example.dllo.vmovie.okhttptool;

/**
 * Created by dllo on 16/8/30.
 */
public class NetTool implements NetInterface {
    private static NetTool ourInstance;

    private NetInterface netInterface;

    public static NetTool getInstance() {
        if (ourInstance == null) {
            synchronized (NetTool.class) {
                if (ourInstance == null) {
                    ourInstance = new NetTool();
                }
            }
        }
        return ourInstance;
    }

    private NetTool() {
        netInterface = new OKHttpUtil();
    }


    @Override
    public void startRequest(String url, OnHttpCallBack<String> callBack) {
        netInterface.startRequest(url, callBack);
    }

    @Override
    public <T> void startRequest(String url, Class<T> tClass, OnHttpCallBack<T> callBack) {
        netInterface.startRequest(url, tClass, callBack);
    }
}
