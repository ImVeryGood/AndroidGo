package com.newdicooker.tempetek.androidgo.com.all.ui.activity.knowledge;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.newdicooker.tempetek.androidgo.R;
import com.newdicooker.tempetek.androidgo.com.all.adapter.knowledge.KnowledgePagerAdapter;
import com.newdicooker.tempetek.androidgo.com.all.base.BaseActivity;
import com.newdicooker.tempetek.androidgo.com.all.bean.KnowledgeBean;
import com.newdicooker.tempetek.androidgo.com.all.bean.MessageEvent;
import com.newdicooker.tempetek.androidgo.com.all.ui.fragment.knowledge.KnowledgeTabFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class KnowledgeActivity extends BaseActivity implements OnTabSelectListener, ViewPager.OnPageChangeListener {
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
            fragments.add(KnowledgeTabFragment.getInstance(childrenBeen.get(0).getId() + ""));
        }
        pagerAdapter.setTitles(stringList);
        viewPager.setAdapter(pagerAdapter);
        pagerAdapter.setFragments(fragments);
        tableLayout.setViewPager(viewPager);
        tableLayout.setCurrentTab(0);
        viewPager.setCurrentItem(0);
    }

    @Override
    protected void initListener() {
        tableLayout.setOnTabSelectListener(this);
        viewPager.addOnPageChangeListener(this);

    }

    @OnClick(R.id.back_image)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onTabSelect(int position) {
        EventBus.getDefault().post(new MessageEvent(childrenBeen.get(position).getId() + ""));
    }

    @Override
    public void onTabReselect(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        EventBus.getDefault().post(new MessageEvent(childrenBeen.get(position).getId() + ""));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
