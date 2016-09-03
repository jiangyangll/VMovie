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
import com.example.dllo.vmovie.netutil.NetUtil;
import com.example.dllo.vmovie.okhttptool.NetTool;
import com.example.dllo.vmovie.okhttptool.OnHttpCallBack;

import java.util.ArrayList;
import java.util.Arrays;

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

        Intent intent = getIntent();
        final String postId = intent.getStringExtra("postId");
        int postid = Integer.valueOf(postId);

        String shareNumber = intent.getStringExtra("shareNumber");
        String likeNumber = intent.getStringExtra("likeNumber");

        share.setText(shareNumber);
        like.setText(likeNumber);

        String url = NetUtil.WEB_LEFT + postid + NetUtil.WEB_RIGHT;
        webView.loadUrl(url);

        commentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent commentIntent = new Intent(ChannelContentActivity.this, ChannelComment.class);
                commentIntent.putExtra("post", postId);
                startActivity(commentIntent);
            }
        });

        NetTool.getInstance().startRequest(NetUtil.COMMENT_LEFT + postid + NetUtil.COMMENT_RIGHT, CommentBean.class, new OnHttpCallBack<CommentBean>() {
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

        NetTool.getInstance().startRequest(NetUtil.NEWEST_DETAIL_LEFT + postid + NetUtil.NEWEST_DETAIL_RIGHT, ChannelContentBean.class, new OnHttpCallBack<ChannelContentBean>() {
            @Override
            public void onSuccess(ChannelContentBean response) {
                String url = response.getData().getContent().getVideo().get(0).getQiniu_url();

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
            }

            @Override
            public void onError(Throwable e) {

            }
        });

        list = new ArrayList<>(Arrays.asList("无线你的无限  英特尔","世界因为不同","只要有梦想  凡事可成真","世界因你而广阔","极速炫彩  掌控天下  霸气十足","天下有双  驰骋世界","人生本有无数可能 让可能变成现实","俯视天下  智握巅峰","奇迹世界  由你掌控"));
        vsTv.setTextContent(list);
    }
}
