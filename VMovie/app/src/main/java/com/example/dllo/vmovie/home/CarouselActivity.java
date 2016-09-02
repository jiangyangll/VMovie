package com.example.dllo.vmovie.home;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.base.BaseActivity;
import com.example.dllo.vmovie.netutil.NetUtil;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/2.
 */
public class CarouselActivity extends BaseActivity implements View.OnClickListener {

    //首页-最新-轮播图详情
    private ImageView backImg, shareImg;
    private WebView webView;

    @Override
    public int setLayout() {
        return R.layout.activity_carousel;
    }

    @Override
    protected void initView() {
        backImg = (ImageView) findViewById(R.id.carousel_image_back);
        shareImg = (ImageView) findViewById(R.id.carousel_image_share);
        webView = (WebView) findViewById(R.id.carousel_webView);
    }

    @Override
    protected void initData() {
        backImg.setOnClickListener(this);
        shareImg.setOnClickListener(this);

        Intent intent = getIntent();
        CarouselData data = intent.getParcelableExtra("params");

        Log.d("CarouselActivity", data.getType() + "   " + data.getParams());

        if (data.getType().equals(1)){
            //第一个网址有问题
            webView.loadUrl(data.getParams());
        }else {
            webView.loadUrl(NetUtil.BannerDescriptionHead + data.getParams() + NetUtil.BannerDescriptionTail);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.carousel_image_back:
                CarouselActivity.this.finish();
                break;

            case R.id.carousel_image_share:
                break;
        }
    }
}
