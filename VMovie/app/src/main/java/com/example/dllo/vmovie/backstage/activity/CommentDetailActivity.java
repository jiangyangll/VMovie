package com.example.dllo.vmovie.backstage.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.backstage.adapter.CommentDetailAdapter;
import com.example.dllo.vmovie.backstage.bean.CommentDetailBean;
import com.example.dllo.vmovie.base.BaseActivity;
import com.example.dllo.vmovie.netutil.NetUtil;
import com.example.dllo.vmovie.okhttptool.NetTool;
import com.example.dllo.vmovie.okhttptool.OnHttpCallBack;

/**
 * Created by 朱麒颢 dllo on 16/8/31.
 * 年轻的战场
 */
public class CommentDetailActivity extends BaseActivity implements OnClickListener {
    private TextView tv_total_num, tv_none_comment;
    private ImageView image_comment_down_arrow, image_back, image_share;
    private Button btn_send;
    private ExpandableListView mExpandableListView;
    private CommentDetailAdapter mDetailAdapter;

    @Override
    public int setLayout() {
        return R.layout.activity_comment_detail;
    }

    @Override
    protected void initView() {
        tv_total_num = (TextView) findViewById(R.id.tv_total_num);
        image_comment_down_arrow = (ImageView) findViewById(R.id.image_comment_down_arrow);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);
        image_comment_down_arrow.setOnClickListener(this);
        mExpandableListView = (ExpandableListView) findViewById(R.id.expand_listView);
        image_back = (ImageView) findViewById(R.id.image_back);
        image_share = (ImageView) findViewById(R.id.image_share);
        image_back.setOnClickListener(this);
        image_share.setOnClickListener(this);
        tv_none_comment = (TextView) findViewById(R.id.tv_none_comment);
    }

    @Override
    protected void initData() {
        mDetailAdapter = new CommentDetailAdapter(this);
        Intent intent = getIntent();
        String s = intent.getStringExtra("id");
        //对评论的详情进行的解析
        NetTool.getInstance().startRequest(NetUtil.COMMENT_LEFT + s + NetUtil.COMMENT_RIGHT, CommentDetailBean.class,
                new OnHttpCallBack<CommentDetailBean>() {
                    @Override
                    public void onSuccess(CommentDetailBean response) {
                        tv_total_num.setText(response.getTotal_num() + "人评论");
                        if (response == null) {
                            tv_none_comment.setVisibility(View.VISIBLE);
                            return;
                        } else {
                            mDetailAdapter.setBean(response);
                            Log.d("CommentDetailActivity", "response:" + response);
                            mExpandableListView.setAdapter(mDetailAdapter);
                            mExpandableListView.setGroupIndicator(null);
                            int groupCount = mExpandableListView.getCount();
                            for (int i = 0; i < groupCount; i++) {
                                mExpandableListView.expandGroup(i);
                            }
                            tv_none_comment.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                break;
            case R.id.image_comment_down_arrow:
                finish();
                break;
            case R.id.image_back:
                finish();
                break;
            case R.id.image_share:
                break;

        }
    }
}
