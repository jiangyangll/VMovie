package com.example.dllo.vmovie.home;

import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.base.BaseActivity;
import com.example.dllo.vmovie.netutil.NetUtil;
import com.example.dllo.vmovie.okhttptool.NetTool;
import com.example.dllo.vmovie.okhttptool.OnHttpCallBack;

/**
 * Created by dllo on 16/9/2.
 */
public class NewComment extends BaseActivity{

    //首页-最新-详情-评论
    private ExpandableListView expandableListView;
    private CommentAdapter adapter;
    private TextView totalNumTv;
    private ImageView returnImg;

    @Override
    public int setLayout() {
        return R.layout.activity_new_comment;
    }

    @Override
    protected void initView() {
        expandableListView = (ExpandableListView) findViewById(R.id.new_comment_elv);
        totalNumTv = (TextView) findViewById(R.id.new_comment_total_num);
        returnImg = (ImageView) findViewById(R.id.new_comment_returnImg);
    }

    @Override
    protected void initData() {
        adapter = new CommentAdapter(this);

        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewComment.this.finish();
            }
        });

        Intent intent = getIntent();
        String postid = intent.getStringExtra("post");

        NetTool.getInstance().startRequest(NetUtil.COMMENT_LEFT + postid + NetUtil.COMMENT_RIGHT, CommentBean.class, new OnHttpCallBack<CommentBean>() {
            @Override
            public void onSuccess(CommentBean response) {
                adapter.setBean(response);
                expandableListView.setAdapter(adapter);

                totalNumTv.setText(response.getTotal_num() + "人评论");

                expandableListView.setGroupIndicator(null);

                int groutCount = response.getData().size();
                for (int i = 0; i < groutCount; i++) {
                    expandableListView.expandGroup(i);
                }
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }
}
