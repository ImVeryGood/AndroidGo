package com.newdicooker.tempetek.androidgo.com.all.ui.fragment.knowledge;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.newdicooker.tempetek.androidgo.R;
import com.newdicooker.tempetek.androidgo.com.all.adapter.home.SearchArticleAdapter;
import com.newdicooker.tempetek.androidgo.com.all.base.BaseFragment;
import com.newdicooker.tempetek.androidgo.com.all.bean.HomeListBean;
import com.newdicooker.tempetek.androidgo.com.all.helper.OkHttpManager;
import com.newdicooker.tempetek.androidgo.com.all.url.NetUrl;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.Request;

/**
 * A simple {@link Fragment} subclass.
 */
public class KnowledgeTabFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private SearchArticleAdapter articleAdapter;
    private int page;
    private static int cId;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_knowledge_tab;
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        articleAdapter = new SearchArticleAdapter(getActivity());
        recyclerView.setAdapter(articleAdapter);
    }

    @Override
    protected void initData() {
        getDates();
    }

    @Override
    protected void initListener() {

    }

    public static KnowledgeTabFragment getInstance(int cId) {
        KnowledgeTabFragment tabFragment = new KnowledgeTabFragment();
        tabFragment.cId = cId;
        return tabFragment;
    }

    public void getDates() {
        Log.d("SSSSSSSSS", "getDates: "+cId);
        OkHttpManager.getInstance().getNet(NetUrl.knowledgeSecondUrl(0, cId), new OkHttpManager.ResultCallback<HomeListBean>() {
            @Override
            public void onFailed(Request request, IOException e) {

            }

            @Override
            public void onSuccess(HomeListBean response) {
                articleAdapter.setArticleDate(response.getData().getDatas());
            }
        });
    }


}
