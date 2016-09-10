package com.example.dllo.vmovie.home;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.base.BaseActivity;
import com.example.dllo.vmovie.customview.VerticalSwitchTextView;
import com.example.dllo.vmovie.dbtool.DaoTools;
import com.example.dllo.vmovie.like.LikeBackstageBean;
import com.example.dllo.vmovie.like.LikeFilmBean;
import com.example.dllo.vmovie.liteormtool.LiteOrmManager;
import com.example.dllo.vmovie.netutil.NetUtil;
import com.example.dllo.vmovie.okhttptool.NetTool;
import com.example.dllo.vmovie.okhttptool.OnHttpCallBack;

import java.util.ArrayList;
import java.util.Arrays;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by dllo on 16/8/31.
 */
public class ChannelContentActivity extends BaseActivity {

    //频道-点击后进入各个子频道-详情信息
    private WebView webView;
    private TextView like, share, comment;
    private ImageView likeImage, shareImage, commentImage, cacheImage, videoBackImg, videoShareImg;
    private VideoView videoView;

    private VerticalSwitchTextView vsTv;
    private ArrayList<String> list;

    private String title;
    private String duration;
    private String shareNum;
    private String ratingNum;
    private String imageUrl;

    private boolean flag = false;
    private LikeFilmBean filmBean;

    private ChannelContentBean channelContentBean;

    private com.example.dllo.vmovie.dbtool.LikeFilmBean bean;

    @Override
    public int setLayout() {

        Vitamio.isInitialized(getApplicationContext());

        return R.layout.channel_content;
    }

    @Override
    protected void initView() {
        webView = (WebView) findViewById(R.id.channel_content_webView);
        like = (TextView) findViewById(R.id.channel_content_tv_like);
        share = (TextView) findViewById(R.id.channel_content_tv_share);
        comment = (TextView) findViewById(R.id.channel_content_tv_comment);
        likeImage = (ImageView) findViewById(R.id.channel_content_img_like);
        shareImage = (ImageView) findViewById(R.id.channel_content_img_share);
        commentImage = (ImageView) findViewById(R.id.channel_content_img_comment);
        cacheImage = (ImageView) findViewById(R.id.channel_content_img_cache);

        videoBackImg = (ImageView) findViewById(R.id.channel_content_video_img_back);
        videoShareImg = (ImageView) findViewById(R.id.channel_content_video_img_share);

        videoView = (VideoView) findViewById(R.id.channel_content_videoView);

        vsTv = (VerticalSwitchTextView) findViewById(R.id.channel_content_VSTV);
    }

    @Override
    protected void initData() {

        likeImage.setImageResource(R.mipmap.side_likes);

        Intent intent = getIntent();
        final String postId = intent.getStringExtra("postId");

        String shareNumber = intent.getStringExtra("shareNumber");
        String likeNumber = intent.getStringExtra("likeNumber");

        share.setText(shareNumber);
        like.setText(likeNumber);

        String url = NetUtil.WEB_LEFT + postId + NetUtil.WEB_RIGHT;
        webView.loadUrl(url);

        commentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent commentIntent = new Intent(ChannelContentActivity.this, ChannelComment.class);
                commentIntent.putExtra("post", postId);
                startActivity(commentIntent);
            }
        });

        NetTool.getInstance().startRequest(NetUtil.COMMENT_LEFT + postId + NetUtil.COMMENT_RIGHT, CommentBean.class, new OnHttpCallBack<CommentBean>() {
            @Override
            public void onSuccess(CommentBean response) {
                comment.setText(response.getTotal_num());
            }

            @Override
            public void onError(Throwable e) {
            }
        });

        videoBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChannelContentActivity.this.finish();
            }
        });

        videoShareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChannelContentActivity.this, "点击分享", Toast.LENGTH_SHORT).show();
            }
        });

        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });

        NetTool.getInstance().startRequest(NetUtil.NEWEST_DETAIL_LEFT + postId + NetUtil.NEWEST_DETAIL_RIGHT, ChannelContentBean.class, new OnHttpCallBack<ChannelContentBean>() {
            @Override
            public void onSuccess(ChannelContentBean response) {
                channelContentBean = response;
                String url = response.getData().getContent().getVideo().get(0).getQiniu_url();

                title = response.getData().getTitle();
                duration = response.getData().getContent().getVideo().get(0).getDuration();
                shareNum = response.getData().getCount_share();
                ratingNum = response.getData().getRating();
                imageUrl = response.getData().getImage();

                videoView.setVideoURI(Uri.parse(url));
                videoView.setMediaController(new MediaController(getApplicationContext()));
                videoView.requestFocus();

                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        // optional need Vitamio 4.0
                        mediaPlayer.setPlaybackSpeed(1.0f);
                    }
                });

                if (DaoTools.getInstance().isSavaFilm(title)) {
                    flag = true;
                    likeImage.setImageResource(R.mipmap.like_image);
                }
            }

            @Override
            public void onError(Throwable e) {
            }
        });

        likeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag) {
                    likeImage.setImageResource(R.mipmap.like_image);
                    filmBean = new LikeFilmBean(title, duration, shareNum, ratingNum, imageUrl, postId);
                    bean = new com.example.dllo.vmovie.dbtool.LikeFilmBean();
                    bean.setTitle(title);
                    LiteOrmManager.getInstance().insert(filmBean);
                    DaoTools.getInstance().insertFilmBean(bean);
                    Toast.makeText(ChannelContentActivity.this, "有品位~", Toast.LENGTH_SHORT).show();
                    flag = true;
                } else {
                    likeImage.setImageResource(R.mipmap.side_likes);
                    LiteOrmManager.getInstance().delete(filmBean);
                    DaoTools.getInstance().deleteFilmBean(bean);
                    Toast.makeText(ChannelContentActivity.this, "品位变了~", Toast.LENGTH_SHORT).show();
                    flag = false;
                }
            }
        });

        list = new ArrayList<>(Arrays.asList("无线你的无限  英特尔", "世界因为不同", "只要有梦想  凡事可成真", "世界因你而广阔", "极速炫彩  掌控天下  霸气十足", "天下有双  驰骋世界", "人生本有无数可能 让可能变成现实", "俯视天下  智握巅峰", "奇迹世界  由你掌控"));
        vsTv.setTextContent(list);
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(channelContentBean.getData().getTitle());
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(channelContentBean.getData().getShare_sub_title());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(channelContentBean.getData().getTitle());
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(channelContentBean.getData().getImage());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(channelContentBean.getData().getShare_link().getWeixin());
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("挺不错哦!!!");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("V电影");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(channelContentBean.getData().getShare_link().getQzone());

        // 启动分享GUI
        oks.show(this);
    }
}
