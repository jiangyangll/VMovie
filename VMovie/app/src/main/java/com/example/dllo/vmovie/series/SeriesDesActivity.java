package com.example.dllo.vmovie.series;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.base.BaseActivity;
import com.example.dllo.vmovie.customview.GrapListView;
import com.example.dllo.vmovie.netutil.NetUtil;
import com.example.dllo.vmovie.okhttptool.NetTool;
import com.example.dllo.vmovie.okhttptool.OnHttpCallBack;
import com.example.dllo.vmovie.series.SeriesDescFromAdapter.OnFromChangeListener;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


/**
 * Created by dllo on 16/9/1.
 */
public class SeriesDesActivity extends BaseActivity implements OnClickListener {

    private TextView tvSeriesDescTitle, tvSeriesDescSmallTitle, tvSeriesDescWeekly, tvSeriesDescUpdate,
         tvSeriesDescTagName, tvSeriesDescContent, tvSeriesDescFollowCount, tvSeriesDescCheckContent, tvShareCount, tvCommentCount;
    private ImageView ivSeriesDescCheckUp, ivSeriesDescCheckDown, ivBack, ivSeriesDescShare;
    private LinearLayout linearShare;
    private static final int UP_STATE = 0;
    private static final int DOWN_STATE = 1;
    private static int mState = UP_STATE;
    private RelativeLayout relativeSeriesDescContent;
    private VideoView videoView;
    private String videoUrl;
    private RecyclerView recyclerSeriesDescFrom;
    private SeriesDescFromAdapter fromAdapter;
    private GrapListView listVideo;
    private SeriesDescVideoListAdapter videoListAdapter;
    private SeriesDescBean seriesDescBean;
    private int fromPositon;
    private int clickFromPosition;
    private int clickPosition;

    @Override
    public int setLayout() {
        return R.layout.activity_series_description;
    }

    @Override
    protected void initView() {
        tvSeriesDescTitle = (TextView) findViewById(R.id.tv_series_description_title);
        tvSeriesDescSmallTitle = (TextView) findViewById(R.id.tv_series_description_small_title);
        tvSeriesDescWeekly = (TextView) findViewById(R.id.tv_series_description_weekly);
        tvSeriesDescUpdate = (TextView) findViewById(R.id.tv_series_description_update);
        tvSeriesDescTagName = (TextView) findViewById(R.id.tv_series_description_tag_name);
        tvSeriesDescContent = (TextView) findViewById(R.id.tv_series_description_content);
        tvSeriesDescFollowCount = (TextView) findViewById(R.id.tv_series_description_count_follow);
        tvSeriesDescCheckContent = (TextView) findViewById(R.id.tv_series_description_check);
        ivSeriesDescCheckUp = (ImageView) findViewById(R.id.iv_series_description_check_up);
        ivSeriesDescCheckDown = (ImageView) findViewById(R.id.iv_series_description_check_down);
        relativeSeriesDescContent = (RelativeLayout) findViewById(R.id.relative_series_description_content);
        ivBack = (ImageView) findViewById(R.id.iv_series_description_back);
        videoView = (VideoView) findViewById(R.id.video_series_description);
        recyclerSeriesDescFrom = (RecyclerView) findViewById(R.id.recycler_series_description_from);
        listVideo = (GrapListView) findViewById(R.id.list_series_description_video);
        tvShareCount = (TextView) findViewById(R.id.tv_series_description_share_num);
        tvCommentCount = (TextView) findViewById(R.id.tv_series_description_count_comment);
        ivSeriesDescShare = (ImageView) findViewById(R.id.iv_series_description_share);
        linearShare = (LinearLayout) findViewById(R.id.linear_series_description_share);
        linearShare.setOnClickListener(this);
        ivSeriesDescShare.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        relativeSeriesDescContent.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        ShareSDK.initSDK(this);
        Intent intent = getIntent();
        String seriesId = intent.getStringExtra("seriesId");
        String url = NetUtil.SERIES_DETAIL_LEFT + seriesId + NetUtil.SERIES_DETAIL_RIGHT;
        final MediaController mediaController = new MediaController(this);
        fromAdapter = new SeriesDescFromAdapter(this);
        videoListAdapter = new SeriesDescVideoListAdapter(this);
        NetTool.getInstance().startRequest(url, SeriesDescBean.class, new OnHttpCallBack<SeriesDescBean>() {
            @Override
            public void onSuccess(SeriesDescBean response) {
                seriesDescBean = response;
                tvSeriesDescTitle.setText("第" + response.getData().getPosts().get(0).getList().get(0).getNumber() + "集  "
                        + response.getData().getPosts().get(0).getList().get(0).getTitle());
                tvSeriesDescSmallTitle.setText(response.getData().getTitle());
                tvSeriesDescFollowCount.setText(response.getData().getCount_follow() + "人订阅");
                tvSeriesDescUpdate.setText("集数: 更新至" + response.getData().getUpdate_to() + "集");
                tvSeriesDescWeekly.setText("更新: " + response.getData().getWeekly());
                tvSeriesDescTagName.setText("类型: " + response.getData().getTag_name());
                tvSeriesDescContent.setText(response.getData().getContent());
                videoUrl = NetUtil.VIDEO_LEFT + response.getData().getPosts().get(0).getList().get(0).getSeries_postid() + NetUtil.VIDEO_RIGHT;
                videoListAdapter.setSeriesDescBean(response);
                videoListAdapter.setFromPosition(0);
                listVideo.setAdapter(videoListAdapter);

                NetTool.getInstance().startRequest(videoUrl, SeriesVideoDescBean.class, new OnHttpCallBack<SeriesVideoDescBean>() {
                    @Override
                    public void onSuccess(SeriesVideoDescBean response) {
                        videoView.setVideoURI(Uri.parse(response.getData().getQiniu_url()));
                        videoView.setMediaController(mediaController);
                        videoView.requestFocus();
                        videoView.start();
                        tvShareCount.setText(response.getData().getCount_share());
                        tvCommentCount.setText(response.getData().getCount_comment());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
                LinearLayoutManager manager = new LinearLayoutManager(SeriesDesActivity.this);
                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerSeriesDescFrom.setLayoutManager(manager);
                fromAdapter.setSeriesDescBean(response);
                recyclerSeriesDescFrom.setAdapter(fromAdapter);
            }

            @Override
            public void onError(Throwable e) {

            }
        });

        fromAdapter.setOnFromChangeListener(new OnFromChangeListener() {
            @Override
            public void onFromChanged(int fromPosition) {
                SeriesDesActivity.this.fromPositon = fromPosition;
                SeriesDesActivity.this.clickFromPosition = fromPosition;
                fromAdapter.setClickPosition(fromPosition);
                videoListAdapter.setFromPosition(fromPosition);
                listVideo.setAdapter(videoListAdapter);
            }
        });

        listVideo.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickPosition = position;
                tvSeriesDescTitle.setText("第" + seriesDescBean.getData().getPosts().get(fromPositon).getList().get(position).getNumber() + "集  "
                        + seriesDescBean.getData().getPosts().get(fromPositon).getList().get(position).getTitle());
                tvSeriesDescSmallTitle.setText(seriesDescBean.getData().getTitle());
                tvSeriesDescFollowCount.setText(seriesDescBean.getData().getCount_follow() + "人订阅");
                tvSeriesDescUpdate.setText("集数: 更新至" + seriesDescBean.getData().getUpdate_to() + "集");
                tvSeriesDescWeekly.setText("更新: " + seriesDescBean.getData().getWeekly());
                tvSeriesDescTagName.setText("类型: " + seriesDescBean.getData().getTag_name());
                tvSeriesDescContent.setText(seriesDescBean.getData().getContent());
                videoUrl = NetUtil.VIDEO_LEFT + seriesDescBean.getData().getPosts().get(fromPositon).getList().get(position).getSeries_postid() + NetUtil.VIDEO_RIGHT;
                NetTool.getInstance().startRequest(videoUrl, SeriesVideoDescBean.class, new OnHttpCallBack<SeriesVideoDescBean>() {
                    @Override
                    public void onSuccess(SeriesVideoDescBean response) {
                        videoView.setVideoURI(Uri.parse(response.getData().getQiniu_url()));
                        videoView.setMediaController(mediaController);
                        videoView.requestFocus();
                        videoView.start();
                        tvShareCount.setText(response.getData().getCount_share());
                        tvCommentCount.setText(response.getData().getCount_comment());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
                videoListAdapter.setSeriesDescBean(seriesDescBean);
                videoListAdapter.setFromPosition(fromPositon);
                videoListAdapter.setClickFromPosition(clickFromPosition);
                videoListAdapter.setClickPosition(position);
                listVideo.setAdapter(videoListAdapter);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relative_series_description_content:
                if (mState == DOWN_STATE) {
                    tvSeriesDescCheckContent.setText("查看全部");
                    tvSeriesDescContent.setMaxLines(2);
                    tvSeriesDescCheckContent.requestLayout();
                    ivSeriesDescCheckUp.setVisibility(View.GONE);
                    ivSeriesDescCheckDown.setVisibility(View.VISIBLE);
                    mState = UP_STATE;
                }else if (mState == UP_STATE){
                    tvSeriesDescContent.setMaxLines(Integer.MAX_VALUE);
                    tvSeriesDescCheckContent.setText("收起简介");
                    tvSeriesDescCheckContent.requestLayout();
                    ivSeriesDescCheckUp.setVisibility(View.VISIBLE);
                    ivSeriesDescCheckDown.setVisibility(View.GONE);
                    mState = DOWN_STATE;
                }
                break;
            case R.id.iv_series_description_back:
                this.onBackPressed();
                break;
            case R.id.iv_series_description_share:
                showShare();
                break;
            case R.id.linear_series_description_share:
                showShare();
                break;
        }
    }

        private void showShare() {

        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(seriesDescBean.getData().getTitle());
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(seriesDescBean.getData().getPosts().get(clickFromPosition).getList().get(clickPosition).getSource_link());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(seriesDescBean.getData().getPosts().get(clickFromPosition).getList().get(clickPosition).getTitle());
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(seriesDescBean.getData().getPosts().get(clickFromPosition).getList().get(clickPosition).getThumbnail());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(seriesDescBean.getData().getPosts().get(clickFromPosition).getList().get(clickPosition).getSource_link());
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("挺好看");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("V电影");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(seriesDescBean.getData().getPosts().get(clickFromPosition).getList().get(clickPosition).getSource_link());

        // 启动分享GUI
        oks.show(this);
    }


}
