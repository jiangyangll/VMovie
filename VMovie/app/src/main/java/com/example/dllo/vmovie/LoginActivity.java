package com.example.dllo.vmovie;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.vmovie.base.BaseActivity;
import com.example.dllo.vmovie.customview.RippleView;
import com.example.dllo.vmovie.customview.StereoView;

import java.util.HashMap;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;


/**
 * Created by dllo on 16/9/6.
 */
public class LoginActivity extends BaseActivity implements OnClickListener, PlatformActionListener {

    private ImageView ivBack, ivTencent, ivSina, ivWeChat;
    private EditText etUserName, etPassword;
    private TextView tvLogin;
    private RippleView rvUsername;
    private RippleView rvPassword;
    private int translateY;
    private StereoView stereoView;

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        stereoView = (StereoView) findViewById(R.id.stereoView);
        ivBack = (ImageView) findViewById(R.id.iv_login_back);
        ivTencent = (ImageView) findViewById(R.id.iv_login_tencent);
        ivSina = (ImageView) findViewById(R.id.iv_login_sina);
        ivWeChat = (ImageView) findViewById(R.id.iv_login_wechat);
        etUserName = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        tvLogin = (TextView) findViewById(R.id.tv_welcome);
        rvUsername = (RippleView) findViewById(R.id.rv_username);
        rvPassword = (RippleView) findViewById(R.id.rv_password);
        ivBack.setOnClickListener(this);
        ivTencent.setOnClickListener(this);
        ivWeChat.setOnClickListener(this);
        ivSina.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        rvUsername.setOnClickListener(this);
        rvPassword.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        ShareSDK.initSDK(this);
        Bmob.initialize(this,"6d4ce637e615c6e4e43aa988a07bbe5a");
        stereoView.setStartScreen(1);
        stereoView.post(new Runnable() {
            @Override
            public void run() {
                int[] location = new int[2];
                stereoView.getLocationOnScreen(location);
                translateY = location[1];
            }
        });
        stereoView.setiStereoListener(new StereoView.IStereoListener() {
            @Override
            public void toPre(int curScreen) {
            }

            @Override
            public void toNext(int curScreen) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_login_back:
                this.onBackPressed();
                break;
            case R.id.iv_login_tencent:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.setPlatformActionListener(this);
                qq.authorize();//单独授权
                qq.showUser(null);//授权并获取用户信息
                break;
            case R.id.iv_login_sina:
                Platform sina = ShareSDK.getPlatform(SinaWeibo.NAME);
                sina.setPlatformActionListener(this);
                sina.authorize();//单独授权
                sina.showUser(null);//授权并获取用户信息
                break;
            case R.id.iv_login_wechat:
                Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                wechat.setPlatformActionListener(this);
                wechat.authorize();//单独授权
                wechat.showUser(null);//授权并获取用户信息
                break;
            case R.id.rv_username:
                rvUsername.setiRippleAnimListener(new RippleView.IRippleAnimListener() {
                    @Override
                    public void onComplete(View view) {
                        stereoView.toPre();
                    }
                });
                break;
            case R.id.rv_password:
                rvPassword.setiRippleAnimListener(new RippleView.IRippleAnimListener() {
                    @Override
                    public void onComplete(View view) {
                        stereoView.toPre();
                    }
                });
                break;
            case R.id.tv_welcome:
                if (TextUtils.isEmpty(etUserName.getText())) {
                    stereoView.setItem(1);
                    Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(etPassword.getText())) {
                    stereoView.setItem(0);
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                startExitAnim();
                break;
        }
    }

    private void startExitAnim() {
        BmobUser.loginByAccount(etUserName.getText().toString(), etPassword.getText().toString(), new LogInListener<UserBean>() {
            @Override
            public void done(UserBean userBean, BmobException e) {
                if (e == null) {
                    VMovieApplication.LOGIN_STATE = true;
                    UserBean userBean1 = BmobUser.getCurrentUser(UserBean.class);
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("headIcon",userBean1.getHeadImageUrl());
                    Log.d("-------", userBean1.getHeadImageUrl());
                    intent.putExtra("userName",userBean1.getUsername());
                    setResult(300,intent);
                    finish();
                }else {
                    return;
                }
            }
        });
        ObjectAnimator animator = ObjectAnimator.ofFloat(stereoView, "translationY", 0, 100, -translateY);
        animator.setDuration(500).start();
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        VMovieApplication.LOGIN_STATE = true;
        PlatformDb db = platform.getDb();
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("headIcon",db.getUserIcon());
        intent.putExtra("userName",db.getUserName());
        setResult(200,intent);
        finish();
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {

    }
}
