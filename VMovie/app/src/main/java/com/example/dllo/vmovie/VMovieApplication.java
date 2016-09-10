package com.example.dllo.vmovie;

import android.app.Application;
import android.content.Context;

import com.example.dllo.vmovie.dbtool.DaoMaster;
import com.example.dllo.vmovie.dbtool.DaoSession;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by dllo on 16/8/31.
 */
public class VMovieApplication extends Application{

    protected static Context context;
    public static Boolean LOGIN_STATE = false;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Fresco.initialize(this);
    }

    public static DaoMaster getDaoMaster(){
        DaoMaster.DevOpenHelper devOpenHelper =
                new DaoMaster.DevOpenHelper(getContext(),"VMovie.db");
        daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        return daoMaster;
    }

    public static DaoSession getDaoSession(){
        if (daoMaster == null) {
            if (daoSession == null) {
                daoSession = getDaoMaster().newSession();
            }
        }
        return daoSession;
    }
}
