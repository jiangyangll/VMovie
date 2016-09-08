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

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by 朱麒颢 dllo on 16/8/31.
 * 年轻的战场
 */
public class CommentDetailActivity extends BaseActivity implements OnClickListener {

    //幕后-幕后文章-详情-评论

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
                showShare();
                break;

        }
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("VMovie");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("挺不错哦!!!");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("V电影");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }


}
