package com.newdicooker.tempetek.androidgo.com.all.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.newdicooker.tempetek.androidgo.R;
import com.newdicooker.tempetek.androidgo.com.all.adapter.home.SearchArticleAdapter;
import com.newdicooker.tempetek.androidgo.com.all.base.BaseActivity;
import com.newdicooker.tempetek.androidgo.com.all.bean.HomeListBean;
import com.newdicooker.tempetek.androidgo.com.all.helper.JudgeUtils;
import com.newdicooker.tempetek.androidgo.com.all.helper.OkHttpManager;
import com.newdicooker.tempetek.androidgo.com.all.inter.ItemClickListener;
import com.newdicooker.tempetek.androidgo.com.all.url.NetUrl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

public class SearchResultActivity extends BaseActivity implements ItemClickListener, OnRefreshLoadmoreListener {
    @BindView(R.id.back_image)
    ImageView backImage;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefresh;
    private Intent mIntent;
    private SearchArticleAdapter searchArticleAdapter;
    private String k;
    private HomeListBean bean;
    private int page;
    private List<HomeListBean.DataBean.DatasBean> list;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        searchArticleAdapter = new SearchArticleAdapter(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(searchArticleAdapter);
    }

    @Override
    protected void initData() {
        mIntent = getIntent();
        k = mIntent.getStringExtra("k");
        titleName.setText(k);
        list = new ArrayList<>();
        smartRefresh.autoRefresh();
    }

    @Override
    protected void initListener() {

        searchArticleAdapter.onClickListener(this);
        smartRefresh.setEnableLoadmore(true);
        smartRefresh.setOnRefreshLoadmoreListener(this);
    }

    @Override
    public void setImmerBar() {
        super.setImmerBar();
    }

    public void searchArticle() {
        OkHttpManager.getInstance().postNet(NetUrl.getSearch(page), new OkHttpManager.ResultCallback<HomeListBean>() {
            @Override
            public void onFailed(Request request, IOException e) {
                setNoRefresh();
            }

            @Override
            public void onSuccess(HomeListBean response) {
                setNoRefresh();
                for (int i = 0; i < response.getData().getDatas().size(); i++) {
                    list.add(response.getData().getDatas().get(i));
                }
                searchArticleAdapter.setArticleDate(list);
            }
        }, new OkHttpManager.Param[]{new OkHttpManager.Param("k", k)});
    }

    public void setNoRefresh() {
        if (smartRefresh.isRefreshing()) {
            smartRefresh.finishRefresh(1000);
        } else if (smartRefresh.isLoading()) {
            smartRefresh.finishLoadmore(1000);
        }
    }

    @OnClick(R.id.back_image)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void setOnItemClickListener(int position) {
        JudgeUtils.judgeToWebActivity(this, bean.getData().getDatas().get(position).getLink());
    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        searchArticle();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 0;
        searchArticle();
    }
}
