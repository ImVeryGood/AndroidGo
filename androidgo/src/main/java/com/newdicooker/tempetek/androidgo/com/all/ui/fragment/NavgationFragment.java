package com.newdicooker.tempetek.androidgo.com.all.ui.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.newdicooker.tempetek.androidgo.R;
import com.newdicooker.tempetek.androidgo.com.all.adapter.navgation.NavgationAdapter;
import com.newdicooker.tempetek.androidgo.com.all.base.BaseFragment;
import com.newdicooker.tempetek.androidgo.com.all.bean.NavgationBean;
import com.newdicooker.tempetek.androidgo.com.all.utils.OkHttpManager;
import com.newdicooker.tempetek.androidgo.com.all.url.NetUrl;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * 导航.
 */
public class NavgationFragment extends BaseFragment implements VerticalTabLayout.OnTabSelectedListener {

    @BindView(R.id.vertical_tab_layout)
    VerticalTabLayout verticalTab;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private boolean needScroll;
    private List<NavgationBean.DataBean> dataBeenList;
    private NavgationAdapter adapter;
    private int index;
    private LinearLayoutManager manager;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_navgation;
    }

    @Override
    protected void initView() {
        manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new NavgationAdapter(R.layout.navgation_recycler_item);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        getNetData();
    }

    @Override
    protected void initListener() {
        verticalTab.addOnTabSelectedListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (needScroll && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    needScroll = false;
                    int indexDistance = index - manager.findFirstVisibleItemPosition();
                    if (indexDistance >= 0 && indexDistance < recyclerView.getChildCount()) {
                        int top = recyclerView.getChildAt(indexDistance).getTop();
                        recyclerView.smoothScrollBy(0, top);
                    }
                   // verticalTab.setTabSelected(newState);
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                needScroll = true;
            }
        });
    }

    @Override
    protected void getNetData() {
        super.getNetData();
        OkHttpManager.getInstance().getNet(NetUrl.navgationUrl(), new OkHttpManager.ResultCallback<NavgationBean>() {
            @Override
            public void onFailed(Request request, IOException e) {

            }

            @Override
            public void onSuccess(NavgationBean response) {
                dataBeenList = response.getData();
                verticalTab.setTabAdapter(new VerticalTabAdapter());
                adapter.setNewData(response.getData());
            }
        });
    }

    @Override
    public void onTabSelected(TabView tab, int position) {
        recyclerView.smoothScrollToPosition(position);

    }

    @Override
    public void onTabReselected(TabView tab, int position) {

    }

    public class VerticalTabAdapter implements TabAdapter {

        @Override
        public int getCount() {
            return dataBeenList == null ? 0 : dataBeenList.size();
        }

        @Override
        public ITabView.TabBadge getBadge(int position) {
            return null;
        }

        @Override
        public ITabView.TabIcon getIcon(int position) {
            return null;
        }

        @Override
        public ITabView.TabTitle getTitle(int position) {
            return new ITabView.TabTitle.Builder()
                    .setContent(dataBeenList.get(position).getName())
                    .setTextColor(0xFF36BC9B, 0xFF757575)
                    .build();
        }

        @Override
        public int getBackground(int position) {
            return 0;
        }
    }
}
