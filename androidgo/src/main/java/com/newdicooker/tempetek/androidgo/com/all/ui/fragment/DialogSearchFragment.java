package com.newdicooker.tempetek.androidgo.com.all.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.newdicooker.tempetek.androidgo.R;
import com.newdicooker.tempetek.androidgo.com.all.bean.HotSearchBean;
import com.newdicooker.tempetek.androidgo.com.all.utils.CommonUtils;
import com.newdicooker.tempetek.androidgo.com.all.utils.JudgeUtils;
import com.newdicooker.tempetek.androidgo.com.all.utils.OkHttpManager;
import com.newdicooker.tempetek.androidgo.com.all.url.NetUrl;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * 搜索dialogFragment
 */
public class DialogSearchFragment extends DialogFragment implements SearchView.OnQueryTextListener, TagFlowLayout.OnTagClickListener {
    @BindView(R.id.search_view)
    SearchView searchView;
    Unbinder unbinder;
    @BindView(R.id.flow_layout)
    TagFlowLayout flowLayout;
    @BindView(R.id.search_history)
    TextView searchHistory;
    @BindView(R.id.clear_history)
    RadioButton clearHistory;
    @BindView(R.id.history_layout)
    RelativeLayout historyLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private View view;

    private List<HotSearchBean.DataBean> hotTabList;

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
        JudgeUtils.judjeToSearchResult(getContext(), query);
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
        OkHttpManager.getInstance().getNet(NetUrl.hotSearch(), new OkHttpManager.ResultCallback<HotSearchBean>() {
            @Override
            public void onFailed(Request request, IOException e) {

            }

            @Override
            public void onSuccess(HotSearchBean response) {
                hotTabList = response.getData();
                setFlow(hotTabList);
            }

        });
    }

    public void setFlow(List<HotSearchBean.DataBean> hotTabList) {
        flowLayout.setAdapter(new TagAdapter<HotSearchBean.DataBean>(hotTabList) {


            @Override
            public View getView(FlowLayout parent, int position, HotSearchBean.DataBean dataBean) {
                TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.hot_flow_item, null);
                textView.setText(dataBean.getName());
                textView.setTextColor(CommonUtils.randomColor());
                return textView;
            }
        });
        flowLayout.setOnTagClickListener(this);
    }

    @Override
    public boolean onTagClick(View view, int position, FlowLayout parent) {
        JudgeUtils.judjeToSearchResult(getContext(), hotTabList.get(position).getName());
        return true;
    }


}
