package com.newdicooker.tempetek.androidgo.com.all.ui.activity.knowledge;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.newdicooker.tempetek.androidgo.R;
import com.newdicooker.tempetek.androidgo.com.all.adapter.knowledge.KnowledgePagerAdapter;
import com.newdicooker.tempetek.androidgo.com.all.base.BaseActivity;
import com.newdicooker.tempetek.androidgo.com.all.bean.KnowledgeBean;
import com.newdicooker.tempetek.androidgo.com.all.ui.fragment.knowledge.KnowledgeTabFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class KnowledgeActivity extends BaseActivity implements OnTabSelectListener {
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.table_layout)
    SlidingTabLayout tableLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private Intent mIntent;
    private KnowledgeBean.DataBean arrayList;
    private KnowledgePagerAdapter pagerAdapter;
    private List<Fragment> fragments;
    private List<KnowledgeBean.DataBean.ChildrenBean> childrenBeen;
    private List<String> stringList;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_knowledge;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mIntent = getIntent();
        fragments = new ArrayList<>();
        stringList = new ArrayList<>();
        arrayList = new Gson().fromJson(mIntent.getStringExtra("tabString"), KnowledgeBean.DataBean.class);
        titleName.setText(mIntent.getStringExtra("title"));
        pagerAdapter = new KnowledgePagerAdapter(getSupportFragmentManager());
        childrenBeen = arrayList.getChildren();
        for (KnowledgeBean.DataBean.ChildrenBean bean : childrenBeen) {
            stringList.add(bean.getName());
            Log.d("SSSSS", "initData: id" + bean.getId());
            fragments.add(KnowledgeTabFragment.getInstance(bean.getId()));
        }
        pagerAdapter.setTitles(stringList);
        viewPager.setAdapter(pagerAdapter);
        pagerAdapter.setFragments(fragments);
        tableLayout.setViewPager(viewPager);
        //tableLayout.setCurrentTab(0);
        //viewPager.setCurrentItem(0);
    }

    @Override
    protected void initListener() {
        tableLayout.setOnTabSelectListener(this);

    }

    @OnClick(R.id.back_image)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onTabSelect(int position) {
        Log.d("SSSSSS", "onTabSelect: position==" + position);
    }

    @Override
    public void onTabReselect(int position) {
        Log.d("SSSSSS", "onTabReselect: position==" + position);
    }
}
