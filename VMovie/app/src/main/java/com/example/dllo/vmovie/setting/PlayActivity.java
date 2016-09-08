package com.example.dllo.vmovie.setting;

import android.view.View;
import android.widget.ImageView;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.base.BaseActivity;

/**
 * Created by dllo on 16/9/7.
 */
public class PlayActivity extends BaseActivity {

    //设置-播放设置
    private ImageView backImg;

    @Override
    public int setLayout() {
        return R.layout.activity_play;
    }

    @Override
    protected void initView() {
        backImg = (ImageView) findViewById(R.id.play_back);
    }

    @Override
    protected void initData() {
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayActivity.this.finish();
            }
        });
    }
}
