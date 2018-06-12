package com.newdicooker.tempetek.androidgo.com.all.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.newdicooker.tempetek.androidgo.R;
import com.newdicooker.tempetek.androidgo.com.all.adapter.knowledge.KnowledgeAdapter;
import com.newdicooker.tempetek.androidgo.com.all.base.BaseFragment;
import com.newdicooker.tempetek.androidgo.com.all.bean.KnowledgeBean;
import com.newdicooker.tempetek.androidgo.com.all.helper.JudgeUtils;
import com.newdicooker.tempetek.androidgo.com.all.helper.OkHttpManager;
import com.newdicooker.tempetek.androidgo.com.all.url.NetUrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

/**
 * A simple {@link Fragment} subclass.
 */
public class KeowledgeFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private KnowledgeAdapter knowledgeAdapter;
    private List<KnowledgeBean.DataBean> beanList;
    private List<KnowledgeBean.DataBean.ChildrenBean> childrenBeen;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_keowledge;
    }

    @Override
    protected void initView() {
        childrenBeen = new ArrayList<>();
        beanList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        knowledgeAdapter = new KnowledgeAdapter();
        recyclerView.setAdapter(knowledgeAdapter);
    }

    @Override
    protected void initData() {
        getDates();
    }

    @Override
    protected void initListener() {
        knowledgeAdapter.setOnItemClickListener(this);
    }

    public void getDates() {
        OkHttpManager.getInstance().getNet(NetUrl.knowledgeUrl(), new OkHttpManager.ResultCallback<KnowledgeBean>() {
            @Override
            public void onFailed(Request request, IOException e) {

            }

            @Override
            public void onSuccess(KnowledgeBean response) {
                beanList = response.getData();
                knowledgeAdapter.setNewData(beanList);

            }

        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        JudgeUtils.judgeToKnowledge(getContext(), beanList.get(position).getName(), new Gson().toJson(beanList.get(position)).toString());
    }
}
