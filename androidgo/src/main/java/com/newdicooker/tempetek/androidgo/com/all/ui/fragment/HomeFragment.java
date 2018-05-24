package com.newdicooker.tempetek.androidgo.com.all.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.newdicooker.tempetek.androidgo.R;
import com.newdicooker.tempetek.androidgo.com.all.adapter.HomeItemAdapter;
import com.newdicooker.tempetek.androidgo.com.all.bean.BannerBean;
import com.newdicooker.tempetek.androidgo.com.all.bean.HomeListBean;
import com.newdicooker.tempetek.androidgo.com.all.helper.OkHttpManager;
import com.newdicooker.tempetek.androidgo.com.all.url.NetUrl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefresh;
    private View view;
    private HomeItemAdapter adapter;
    private BannerBean bannerBean;
    private HomeListBean homeListBean;
    private int page;
    private List<HomeListBean.DataBean.DatasBean> homeList;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        setData();
        setListener();
        return view;
    }

    private void setListener() {
        smartRefresh.setOnRefreshListener(this);
        smartRefresh.setOnLoadmoreListener(this);
        smartRefresh.autoRefresh();

    }

    private void setData() {
        homeList = new ArrayList<>();

    }

    private void initView() {
        recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new HomeItemAdapter(getContext());
        recycler.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void getBanner() {
        OkHttpManager.getInstance().getNet(NetUrl.homeBanner(), new OkHttpManager.ResultCallback() {
            @Override
            public void onFailed(Request request, IOException e) {

            }

            @Override
            public void onSuccess(String response) {
                if (!TextUtils.isEmpty(response)) {
                    bannerBean = new Gson().fromJson(response, BannerBean.class);
                    adapter.setBannerList(bannerBean.getData());
                }
            }
        });
    }

    public void getArticle() {
        OkHttpManager.getInstance().getNet(NetUrl.homeListUrl(page), new OkHttpManager.ResultCallback() {
            @Override
            public void onFailed(Request request, IOException e) {

            }

            @Override
            public void onSuccess(String response) {
                setNoRefresh();
                if (!TextUtils.isEmpty(response)) {
                    homeListBean = new Gson().fromJson(response, HomeListBean.class);
                    for (int i = 0; i < homeListBean.getData().getDatas().size(); i++) {
                        homeList.add(homeListBean.getData().getDatas().get(i));
                    }
                    adapter.setArticleList(homeList);

                }

            }
        });
    }

    /*刷新*/
    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getBanner();
        getArticle();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        getArticle();


    }

    public void setNoRefresh() {
        if (smartRefresh.isRefreshing()) {
            homeList.clear();
            smartRefresh.finishRefresh();
        }
        if (smartRefresh.isLoading()) {
            smartRefresh.finishLoadmore();
        }
    }
}
