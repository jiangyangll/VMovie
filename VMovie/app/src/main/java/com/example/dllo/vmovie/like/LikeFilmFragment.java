package com.example.dllo.vmovie.like;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.base.BaseFragment;
import com.example.dllo.vmovie.home.NewDetail;
import com.example.dllo.vmovie.liteormtool.LiteOrmManager;
import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/6.
 */
public class LikeFilmFragment extends BaseFragment {

    //喜欢-影片
    private ListView listView;
    private LikeFilmAdapter adapter;
    private List<LikeFilmBean> likeFilmList;

    @Override
    protected int setLayout() {
        return R.layout.fragment_like_film;
    }

    @Override
    protected void initView(View view) {
        listView = (ListView) view.findViewById(R.id.like_film);
    }

    @Override
    protected void initData() {

        adapter = new LikeFilmAdapter(getContext());
        likeFilmList = LiteOrmManager.getInstance().queryAll(LikeFilmBean.class);
        adapter.setBean(likeFilmList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), NewDetail.class);
                intent.putExtra("postId", likeFilmList.get(position).getPostId());
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                LiteOrmManager.getInstance().delete(likeFilmList.get(position));
                adapter.setBean(likeFilmList);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}
