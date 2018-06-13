package com.newdicooker.tempetek.androidgo.com.all.ui.fragment.knowledge;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.newdicooker.tempetek.androidgo.R;
import com.newdicooker.tempetek.androidgo.com.all.adapter.home.SearchArticleAdapter;
import com.newdicooker.tempetek.androidgo.com.all.base.BaseFragment;
import com.newdicooker.tempetek.androidgo.com.all.bean.HomeListBean;
import com.newdicooker.tempetek.androidgo.com.all.bean.MessageEvent;
import com.newdicooker.tempetek.androidgo.com.all.helper.JudgeUtils;
import com.newdicooker.tempetek.androidgo.com.all.helper.OkHttpManager;
import com.newdicooker.tempetek.androidgo.com.all.inter.ItemClickListener;
import com.newdicooker.tempetek.androidgo.com.all.url.NetUrl;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.Request;

/**
 * A simple {@link Fragment} subclass.
 */
public class KnowledgeTabFragment extends BaseFragment implements ItemClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private SearchArticleAdapter articleAdapter;
    private int page;
    private String message;
    private HomeListBean.DataBean bean;

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
        articleAdapter.onClickListener(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent) {
        message = messageEvent.getMessage();
        getDates();
    }

    public static KnowledgeTabFragment getInstance(String cId) {
        KnowledgeTabFragment tabFragment = new KnowledgeTabFragment();
        tabFragment.message = cId;
        return tabFragment;
    }

    public void getDates() {
        OkHttpManager.getInstance().getNet(NetUrl.knowledgeSecondUrl(0, message), new OkHttpManager.ResultCallback<HomeListBean>() {
            @Override
            public void onFailed(Request request, IOException e) {

            }

            @Override
            public void onSuccess(HomeListBean response) {
                bean = response.getData();
                articleAdapter.setArticleDate(response.getData().getDatas());
            }
        });
    }


    @Override
    public void setOnItemClickListener(int position) {
        JudgeUtils.judgeToWebActivity(getContext(), bean.getDatas().get(position).getLink());
    }
}
