package com.example.dllo.vmovie;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by dllo on 16/8/31.
 */
public class VMovieApplication extends Application{

    protected static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;

        Fresco.initialize(this);
    }
}
