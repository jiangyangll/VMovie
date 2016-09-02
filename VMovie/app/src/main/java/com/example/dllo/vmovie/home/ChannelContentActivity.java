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
import com.example.dllo.vmovie.netutil.NetUtil;
import com.example.dllo.vmovie.okhttptool.NetTool;
import com.example.dllo.vmovie.okhttptool.OnHttpCallBack;

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
    private ImageView likeImage, shareImage, commentImage, cacheImage, videoBackImg, videoCacheImg;
    private VideoView videoView;

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
        videoCacheImg = (ImageView) findViewById(R.id.channel_content_video_img_cache);

        videoView = (VideoView) findViewById(R.id.channel_content_videoView);
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

        videoCacheImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChannelContentActivity.this, "点击缓存", Toast.LENGTH_SHORT).show();
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
//                videoView.start();
            }

            @Override
            public void onError(Throwable e) {

            }
        });



    }
}
