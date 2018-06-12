package com.newdicooker.tempetek.androidgo.com.all.adapter.knowledge;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by SunPengCheng
 * on 2018/6/11
 * 邮箱：13027699936@163.com.
 * version 2.0.4
 */

public class KnowledgePagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> list;

    public KnowledgePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public void setTitles(List<String> list) {
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
