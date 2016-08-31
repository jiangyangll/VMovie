package com.example.dllo.vmovie.backstage.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.backstage.bean.AllDetailBean;
import com.example.dllo.vmovie.base.BaseActivity;
import com.example.dllo.vmovie.netutil.NetUtil;
import com.example.dllo.vmovie.okhttptool.NetTool;
import com.example.dllo.vmovie.okhttptool.OnHttpCallBack;

/**
 * Created by 朱麒颢 dllo on 16/8/31.
 * 年轻的战场
 */
public class AllDetailActivity extends BaseActivity implements OnClickListener {
    private TextView tv_title, tv_share_sub_title, tv_cate,
            tv_count_like, tv_count_share, tv_count_comment;
    private ImageView image_back, image_share, image_side_likes,
            image_bottom_share, image_comment;
    private WebView mWebView;

    @Override
    public int setLayout() {
        return R.layout.activity_all_detail;
    }

    @Override
    protected void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
//        tv_share_sub_title = (TextView) findViewById(R.id.tv_share_sub_title);
//        tv_cate = (TextView) findViewById(R.id.tv_cate);
        tv_count_like = (TextView) findViewById(R.id.tv_count_like);
        tv_count_share = (TextView) findViewById(R.id.tv_count_share);
        tv_count_comment = (TextView) findViewById(R.id.tv_count_comment);
        image_back = (ImageView) findViewById(R.id.image_back);
        image_share = (ImageView) findViewById(R.id.image_share);
        image_side_likes = (ImageView) findViewById(R.id.image_side_likes);
        image_bottom_share = (ImageView) findViewById(R.id.image_bottom_share);
        image_comment = (ImageView) findViewById(R.id.image_comment);
        image_back.setOnClickListener(this);
        image_share.setOnClickListener(this);
        image_side_likes.setOnClickListener(this);
        image_bottom_share.setOnClickListener(this);
        image_comment.setOnClickListener(this);
        mWebView = (WebView) findViewById(R.id.webView);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String newId = intent.getStringExtra("id");
        int id = Integer.parseInt(newId);
        NetTool.getInstance().startRequest(NetUtil.BACKSTAGE_DETAIL + id, AllDetailBean.class, new OnHttpCallBack<AllDetailBean>() {
            @Override
            public void onSuccess(AllDetailBean response) {
//                tv_title.setText(response.getData().getTitle());
//                tv_share_sub_title.setText(response.getData().getShare_sub_title());
//                tv_cate.setText("#" + response.getData().getCate().get(0) + "#");
                tv_count_comment.setText(response.getData().getCount_comment());
                tv_count_like.setText(response.getData().getCount_like());
                tv_count_share.setText(response.getData().getCount_share());
            }

            @Override
            public void onError(Throwable e) {

            }
        });

        mWebView.loadUrl(NetUtil.WEB_LEFT + id + NetUtil.WEB_RIGHT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.image_share:
                break;
            case R.id.image_side_likes:
                break;
            case R.id.image_bottom_share:
                break;
            case R.id.image_comment:
                break;
        }

    }
}
