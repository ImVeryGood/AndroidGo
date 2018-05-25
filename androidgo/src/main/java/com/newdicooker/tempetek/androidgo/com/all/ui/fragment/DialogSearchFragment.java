package com.newdicooker.tempetek.androidgo.com.all.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.newdicooker.tempetek.androidgo.R;
import com.newdicooker.tempetek.androidgo.com.all.helper.OkHttpManager;
import com.newdicooker.tempetek.androidgo.com.all.url.NetUrl;
import com.zhy.view.flowlayout.FlowLayout;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * 搜索dialogFragment
 */
public class DialogSearchFragment extends DialogFragment implements SearchView.OnQueryTextListener {
    @BindView(R.id.search_view)
    SearchView searchView;
    Unbinder unbinder;
    @BindView(R.id.flow_layout)
    FlowLayout flowLayout;
    @BindView(R.id.search_history)
    TextView searchHistory;
    @BindView(R.id.clear_history)
    RadioButton clearHistory;
    @BindView(R.id.history_layout)
    RelativeLayout historyLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private View view;

    public DialogSearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dialog_search, container, false);
        unbinder = ButterKnife.bind(this, view);
        setSearchView();
        getHotSearch();

        return view;
    }

    public void setSearchView() {
        searchView.setIconified(false);
        //设置搜索框直接展开显示。左侧有放大镜(在搜索框外) 右侧无叉叉 有输入内容后有叉叉 不能关闭搜索框
        searchView.setIconifiedByDefault(false);
        searchView.onActionViewExpanded();
        searchView.setOnQueryTextListener(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /*输入监听*/
    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    /*点击搜索触发*/
    @Override
    public boolean onQueryTextChange(String newText) {

        return false;
    }

    @OnClick({R.id.clear_history, R.id.history_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clear_history:
                break;

        }
    }

    public void getHotSearch() {
        OkHttpManager.getInstance().getNet(NetUrl.hotSearch(), new OkHttpManager.ResultCallback() {
            @Override
            public void onFailed(Request request, IOException e) {

            }

            @Override
            public void onSuccess(String response) {
                if (!TextUtils.isEmpty(response)) {

                }

            }
        });
    }

    public void setFlow() {
        
    }
}
