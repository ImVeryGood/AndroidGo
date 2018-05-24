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
import com.newdicooker.tempetek.androidgo.com.all.helper.OkHttpManager;
import com.newdicooker.tempetek.androidgo.com.all.url.NetUrl;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;
    private View view;
    private HomeItemAdapter adapter;
    private BannerBean bannerBean;

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
        return view;
    }

    private void setData() {
        getBanner();
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
}
