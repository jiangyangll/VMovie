package com.example.dllo.vmovie.search;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.base.BaseActivity;

/**
 * Created by dllo on 16/9/8.
 */
public class SearchDetailActivity extends BaseActivity {

    //搜索-详情
    private ImageView backImage;
    private WebView webView;

    @Override
    public int setLayout() {
        return R.layout.activity_search_detail;
    }

    @Override
    protected void initView() {
        backImage = (ImageView) findViewById(R.id.search_detail_back);
        webView = (WebView) findViewById(R.id.search_detail_webView);
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        String requestUrl = intent.getStringExtra("requestUrl");

        webView.loadUrl(requestUrl);

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchDetailActivity.this.finish();
            }
        });

    }
}
