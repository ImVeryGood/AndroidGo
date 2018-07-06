package com.newdicooker.tempetek.androidgo.com.all.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.newdicooker.tempetek.androidgo.R;
import com.newdicooker.tempetek.androidgo.com.all.base.BaseActivity;
import com.newdicooker.tempetek.androidgo.com.all.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class WebActivity extends BaseActivity {
    private String url;
    private Intent intent;
    @BindView(R.id.web_view)
    WebView webView;
    private Intent mIntent;
    private String message;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        mIntent = getIntent();
        message = mIntent.getStringExtra("message");
        if ("500".equals(message)) {
            Log.d("SSSSSS", "initView: ");
            setDialog();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent) {
        if (messageEvent.getMessage().equals("500")) {
            setDialog();
        }
    }

    @Override
    protected void initData() {

        intent = getIntent();
        url = intent.getStringExtra("url");
        initWebView();
    }

    @Override
    protected void initListener() {

    }

    public void initWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setBlockNetworkImage(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setUseWideViewPort(true); // 关键点
        webSettings.setPluginState(WebSettings.PluginState.ON);
        //支持js脚本
        webSettings.setJavaScriptEnabled(true);
        //支持缩放
        webSettings.setSupportZoom(true);
        //支持内容重新布局
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //多窗口
        webSettings.supportMultipleWindows();
        //当webview调用requestFocus时为webview设置节点
        webSettings.setNeedInitialFocus(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //支持通过JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //支持自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        //优先使用缓存:
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //提高渲染的优先级
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 开启H5(APPCache)缓存功能
        webSettings.setAppCacheEnabled(true);
        // 开启 DOM storage 功能
        // webSettings.setDomStorageEnabled(true);
        // 应用可以有数据库
        // webSettings.setDatabaseEnabled(true);
        // 可以读取文件缓存(manifest生效)
        webSettings.setAllowFileAccess(true);
        webView.canGoBack();
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient());
    }

    public void setDialog() {
        new AlertDialog.Builder(this).
                setMessage("下线").
                setPositiveButton("确认", null).
                setNegativeButton("取消", null).
                create().
                show();

    }
}
