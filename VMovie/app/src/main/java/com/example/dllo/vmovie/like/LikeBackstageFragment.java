package com.example.dllo.vmovie.like;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.backstage.activity.AllDetailActivity;
import com.example.dllo.vmovie.base.BaseFragment;
import com.example.dllo.vmovie.home.ChannelContentActivity;
import com.example.dllo.vmovie.liteormtool.LiteOrmManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/6.
 */
public class LikeBackstageFragment extends BaseFragment {

    //喜欢-幕后
    private ListView listView;
    private LikeBackstageAdapter adapter;
    private List<LikeBackstageBean> backBeanList;

    @Override
    protected int setLayout() {
        return R.layout.fragment_like_backstage;
    }

    @Override
    protected void initView(View view) {
        listView = (ListView) view.findViewById(R.id.like_backstage);
    }

    @Override
    protected void initData() {

        adapter = new LikeBackstageAdapter(getContext());
        backBeanList = LiteOrmManager.getInstance().queryAll(LikeBackstageBean.class);
        adapter.setBean(backBeanList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), AllDetailActivity.class);
                intent.putExtra("id", backBeanList.get(position).getPostId());
                startActivity(intent);
            }
        });
    }
}
