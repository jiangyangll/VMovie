package com.example.dllo.vmovie.series;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.base.BaseActivity;
import com.example.dllo.vmovie.dbtool.DaoTools;
import com.example.dllo.vmovie.dbtool.SubScribeBean;
import com.example.dllo.vmovie.netutil.NetUtil;
import com.example.dllo.vmovie.okhttptool.NetTool;
import com.example.dllo.vmovie.okhttptool.OnHttpCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 */
public class SubscribeActivity extends BaseActivity {

    private ListView lvSubscribe;
    private List<String> seriesIdList;
    private List<String> urlList;
    private TextView tvSubscribeBlank;
    private List<SubscribeListBean> beanList;
    private SubscribeAdapter subscribeAdapter;
    private ImageView ivBack;

    @Override
    public int setLayout() {
        return R.layout.activity_subscribe;
    }

    @Override
    protected void initView() {
        lvSubscribe = (ListView) findViewById(R.id.lv_subscribe);
        tvSubscribeBlank = (TextView) findViewById(R.id.tv_subscribe_blank);
        ivBack = (ImageView) findViewById(R.id.iv_subscribe_back);
    }

    @Override
    protected void initData() {
        ivBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SubscribeActivity.this.onBackPressed();
            }
        });
        seriesIdList = new ArrayList<>();
        urlList = new ArrayList<>();
        beanList = new ArrayList<>();
        if (DaoTools.getInstance().getAllSubscribe().size() == 0) {
            tvSubscribeBlank.setVisibility(View.VISIBLE);
            return;
        }
        tvSubscribeBlank.setVisibility(View.GONE);
        for (SubScribeBean subScribeBean : DaoTools.getInstance().getAllSubscribe()) {
            seriesIdList.add(subScribeBean.getSeriesId());
        }
        for (String s : seriesIdList) {
            urlList.add(NetUtil.SERIES_DETAIL_LEFT + s + NetUtil.SERIES_DETAIL_RIGHT);
        }

        subscribeAdapter = new SubscribeAdapter(this);

        for (String s : urlList) {
            NetTool.getInstance().startRequest(s, SeriesDescBean.class, new OnHttpCallBack<SeriesDescBean>() {
                @Override
                public void onSuccess(SeriesDescBean response) {
                    for (int i = 0; i < response.getData().getPosts().size(); i++) {
                        for (int j = 0; j < response.getData().getPosts().get(i).getList().size(); j++) {
                            SubscribeListBean listBean = new SubscribeListBean();
                            listBean.setAddtime(response.getData().getPosts().get(i).getList().get(j).getAddtime());
                            listBean.setDuration(response.getData().getPosts().get(i).getList().get(j).getDuration());
                            listBean.setNumber(response.getData().getPosts().get(i).getList().get(j).getNumber());
                            listBean.setSeries_postid(response.getData().getPosts().get(i).getList().get(j).getSeries_postid());
                            listBean.setSource_link(response.getData().getPosts().get(i).getList().get(j).getSource_link());
                            listBean.setThumbnail(response.getData().getPosts().get(i).getList().get(j).getThumbnail());
                            listBean.setTitle(response.getData().getPosts().get(i).getList().get(j).getTitle());
                            Log.d("SubscribeActivity", listBean.toString());
                            beanList.add(listBean);
                        }
                    }
                    subscribeAdapter.setBeanList(beanList);
                    lvSubscribe.setAdapter(subscribeAdapter);
                }

                @Override
                public void onError(Throwable e) {

                }
            });



        }

    }
}
