package com.example.dllo.vmovie.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.example.dllo.vmovie.MainActivity;
import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dllo on 16/9/10.
 */
public class WelcomeActivity extends BaseActivity implements View.OnClickListener {
    private int index = 5;
    private TextView textView;
    private Timer timer;
    private TimerTask timerTask;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            textView.setText("正在加载,请稍候..." + index--);
            if (index == 0) {
                startNextPage();
            }
            return false;
        }
    });

    private void startNextPage() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        textView = (TextView) findViewById(R.id.welcome_tv);
    }

    @Override
    protected void initData() {

        textView.setOnClickListener(this);

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        };

        timer.schedule(timerTask, 0, 1000);

        //如果不是第一次启动,获取false,不执行
        SharedPreferences getSp = getSharedPreferences("splash", MODE_PRIVATE);
        if (getSp.getBoolean("isFirst", true)) {
            Intent intent = new Intent(WelcomeActivity.this, SplashActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        startNextPage();
    }
}
