package com.example.dllo.vmovie.home;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.base.BaseActivity;
import com.example.dllo.vmovie.like.LikeFilmBean;
import com.example.dllo.vmovie.liteormtool.LiteOrmManager;
import com.example.dllo.vmovie.netutil.NetUtil;
import com.example.dllo.vmovie.okhttptool.NetTool;
import com.example.dllo.vmovie.okhttptool.OnHttpCallBack;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.List;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by dllo on 16/9/2.
 */
public class NewDetail extends BaseActivity {

    //首页-最新-详情
    private WebView webView;
    private TextView like, share, comment;
    private ImageView likeImage, shareImage, commentImage, cacheImage, videoBackImg, videoShareImg;
    private VideoView videoView;
    private String title;
    private String duration;
    private String shareNum;
    private String ratingNum;
    private String imageUrl;

    private boolean flag = false;
    private LikeFilmBean filmBean;

    @Override
    public int setLayout() {
        Vitamio.isInitialized(getApplicationContext());

        return R.layout.activity_newdetail;
    }

    @Override
    protected void initView() {
        webView = (WebView) findViewById(R.id.new_detail_webView);
        like = (TextView) findViewById(R.id.new_detail_tv_like);
        share = (TextView) findViewById(R.id.new_detail_tv_share);
        comment = (TextView) findViewById(R.id.new_detail_tv_comment);
        likeImage = (ImageView) findViewById(R.id.new_detail_img_like);
        shareImage = (ImageView) findViewById(R.id.new_detail_img_share);
        commentImage = (ImageView) findViewById(R.id.new_detail_img_comment);
        cacheImage = (ImageView) findViewById(R.id.new_detail_img_cache);
        videoBackImg = (ImageView) findViewById(R.id.new_detail_video_img_back);
        videoShareImg = (ImageView) findViewById(R.id.new_detail_video_img_share);
        videoView = (VideoView) findViewById(R.id.new_detail_videoView);
    }

    @Override
    protected void initData() {

        likeImage.setImageResource(R.mipmap.side_likes);

        Intent intent = getIntent();
        final String postId = intent.getStringExtra("postId");

        NetTool.getInstance().startRequest(NetUtil.NEWEST_DETAIL_LEFT + postId + NetUtil.NEWEST_DETAIL_RIGHT, NewDetailBean.class, new OnHttpCallBack<NewDetailBean>() {
            @Override
            public void onSuccess(NewDetailBean response) {
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
                like.setText(response.getData().getCount_like());
                share.setText(response.getData().getCount_share());

                //获取数据存储于数据库中
                title = response.getData().getTitle();
                duration = response.getData().getContent().getVideo().get(0).getDuration();
                shareNum = response.getData().getCount_share();
                ratingNum = response.getData().getRating();
                imageUrl = response.getData().getImage();

            }

            @Override
            public void onError(Throwable e) {
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

        webView.loadUrl(NetUtil.WEB_LEFT + postId + NetUtil.WEB_RIGHT);

        videoBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewDetail.this.finish();
            }
        });

        cacheImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewDetail.this, "开始缓存", Toast.LENGTH_SHORT).show();
            }
        });

        commentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newCommentIntent = new Intent(NewDetail.this, NewComment.class);
                newCommentIntent.putExtra("post", postId);
                startActivity(newCommentIntent);
            }
        });

        videoShareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewDetail.this, "点击分享", Toast.LENGTH_SHORT).show();
            }
        });

        //判断状态,如果没有
//        if (LiteOrmManager.getInstance().queryByWhere(LikeFilmBean.class, LikeFilmBean.title) == null) {
//            flag = true;
//            likeImage.setImageResource(R.mipmap.like_image);
//        }

        likeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag) {
                    likeImage.setImageResource(R.mipmap.like_image);
                    filmBean = new LikeFilmBean(title, duration, shareNum, ratingNum, imageUrl, postId);
                    LiteOrmManager.getInstance().insert(filmBean);
                    Toast.makeText(NewDetail.this, "有品位~", Toast.LENGTH_SHORT).show();
                    flag = true;
                } else {
                    likeImage.setImageResource(R.mipmap.side_likes);
                    LiteOrmManager.getInstance().delete(filmBean);
                    Toast.makeText(NewDetail.this, "品位变了~", Toast.LENGTH_SHORT).show();
                    flag = false;
                }
            }
        });
    }
}
