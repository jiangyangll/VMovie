package com.example.dllo.vmovie.backstage.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.backstage.bean.AllDetailBean;
import com.example.dllo.vmovie.base.BaseActivity;
import com.example.dllo.vmovie.dbtool.DaoTools;
import com.example.dllo.vmovie.dbtool.LikeBackStageBean;
import com.example.dllo.vmovie.like.LikeBackstageBean;
import com.example.dllo.vmovie.liteormtool.LiteOrmManager;
import com.example.dllo.vmovie.netutil.NetUtil;
import com.example.dllo.vmovie.okhttptool.NetTool;
import com.example.dllo.vmovie.okhttptool.OnHttpCallBack;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dllo on 16/8/31.
 * 年轻的战场
 */
public class AllDetailActivity extends BaseActivity implements OnClickListener {

    //幕后-幕后文章-详情
    private TextView tv_count_like, tv_count_share, tv_count_comment;
    private ImageView image_back, image_share, image_side_likes,
            image_bottom_share, image_comment;
    private WebView mWebView;

    private String postId;
    private AllDetailBean mAllDetailBean;

    private String title;
    private String shareNum;
    private String likeNum;
    private String imageUrl;

    private boolean flag = false;
    private LikeBackstageBean backstageBean;

    private LikeBackStageBean bean;

    @Override
    public int setLayout() {
        return R.layout.activity_all_detail;
    }

    @Override
    protected void initView() {
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

        mWebView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllDetailActivity.this, AllDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

        image_side_likes.setImageResource(R.mipmap.side_likes);

        Intent intent = getIntent();

        postId = intent.getStringExtra("id");
        NetTool.getInstance().startRequest(NetUtil.BACKSTAGE_DETAIL + postId, AllDetailBean.class, new OnHttpCallBack<AllDetailBean>() {

            @Override
            public void onSuccess(AllDetailBean response) {
                mAllDetailBean = response;
                tv_count_comment.setText(response.getData().getCount_comment());
                tv_count_like.setText(response.getData().getCount_like());
                tv_count_share.setText(response.getData().getCount_share());

                title = response.getData().getTitle();
                shareNum = response.getData().getCount_share();
                likeNum = response.getData().getCount_like();
                imageUrl = response.getData().getImage();

                if (DaoTools.getInstance().isSavaBackStage(title)) {
                    flag = true;
                    image_side_likes.setImageResource(R.mipmap.like_image);
                }
            }

            @Override
            public void onError(Throwable e) {
            }
        });

        WebViewClient webViewClient = new WebViewClient();
        mWebView.setWebViewClient(webViewClient);
        mWebView.loadUrl(NetUtil.WEB_LEFT + postId + NetUtil.WEB_RIGHT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.image_share:
                showShare();
                break;
            case R.id.image_side_likes:
                if (!flag) {
                    image_side_likes.setImageResource(R.mipmap.like_image);
                    backstageBean = new LikeBackstageBean(postId, title, imageUrl, shareNum, likeNum);
                    bean = new LikeBackStageBean();
                    bean.setTitle(title);
                    LiteOrmManager.getInstance().insert(backstageBean);
                    DaoTools.getInstance().insertBackStage(bean);
                    Toast.makeText(this, "有品位~", Toast.LENGTH_SHORT).show();
                    flag = true;
                } else {
                    image_side_likes.setImageResource(R.mipmap.side_likes);
                    LiteOrmManager.getInstance().delete(backstageBean);
                    DaoTools.getInstance().deleteBackStage(bean);
                    Toast.makeText(this, "品位变了~", Toast.LENGTH_SHORT).show();
                    flag = false;
                }
                break;
            case R.id.image_bottom_share:
                showShare();
                break;
            case R.id.image_comment:
                Intent intent = new Intent(AllDetailActivity.this, CommentDetailActivity.class);
                intent.putExtra("id", postId);

                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                startActivity(intent);
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
        oks.setTitle(mAllDetailBean.getData().getTitle());
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(mAllDetailBean.getData().getShare_sub_title());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(mAllDetailBean.getData().getTitle());
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(mAllDetailBean.getData().getImage());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(mAllDetailBean.getData().getShare_link().getWeixin());
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("挺不错哦!!!");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("V电影");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(mAllDetailBean.getData().getShare_link().getQzone());

        // 启动分享GUI
        oks.show(this);
    }


}
