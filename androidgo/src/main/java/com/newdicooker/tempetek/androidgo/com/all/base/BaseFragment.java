package com.newdicooker.tempetek.androidgo.com.all.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newdicooker.tempetek.androidgo.com.all.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by SunPengCheng
 * on 2018/6/11
 * 邮箱：13027699936@163.com.
 * version 2.0.4
 */

public abstract class BaseFragment extends Fragment {
    private View mContentView;
    private Context mContext;
    Unbinder unbinder;

    public BaseFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(setLayoutResourceID(), container, false);
        unbinder = ButterKnife.bind(this, mContentView);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        initView();
        initData();
        initListener();
        return mContentView;
    }

    /**
     * 用于返回布局资源
     *
     * @return 布局资源id
     */
    protected abstract int setLayoutResourceID();

    /**
     * 一些view的操作
     */
    protected abstract void initView();

    /**
     * 一些date的操作
     */
    protected abstract void initData();

    /**
     * 监听
     */
    protected abstract void initListener();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent) {
    }

    protected void getNetData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
