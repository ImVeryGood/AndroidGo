package com.newdicooker.tempetek.androidgo;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.newdicooker.tempetek.androidgo.com.all.base.BaseActivity;
import com.newdicooker.tempetek.androidgo.com.all.helper.BottomNavigationViewHelper;
import com.newdicooker.tempetek.androidgo.com.all.ui.fragment.DialogSearchFragment;
import com.newdicooker.tempetek.androidgo.com.all.ui.fragment.HomeFragment;
import com.newdicooker.tempetek.androidgo.com.all.ui.fragment.KeowledgeFragment;
import com.newdicooker.tempetek.androidgo.com.all.ui.fragment.NavgationFragment;
import com.newdicooker.tempetek.androidgo.com.all.ui.fragment.ProjectFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.frame_group)
    FrameLayout frameGroup;
    @BindView(R.id.search_icon)
    ImageView searchIcon;
    @BindView(R.id.navigation_view)
    BottomNavigationView navigationView;
    private HomeFragment homeF;
    private KeowledgeFragment keowledgeF;
    private NavgationFragment navgationF;
    private ProjectFragment projectF;
    private List<Fragment> fragList;
    private int mPosition;
    private FragmentTransaction trans;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setImmerBar() {
        super.setImmerBar();
        mImmersionBar.fullScreen(true).statusBarColor(R.color.main_status_bar_blue).init();
    }

    @Override
    protected void initData() {
        super.initData();

    }


    @Override
    protected void initView() {
        super.initView();
        setBottomNagiation();
        setFragment();

    }


    @Override
    protected void initListener() {
        super.initListener();
        setNestedListener();
    }

    private void setNestedListener() {
        navigationView.setOnNavigationItemSelectedListener(this);

    }


    private void setBottomNagiation() {
        BottomNavigationViewHelper.disableShiftMode(navigationView);

    }

    private void setFragment() {
        fragList = new ArrayList<>();
        homeF = new HomeFragment();
        keowledgeF = new KeowledgeFragment();
        projectF = new ProjectFragment();
        navgationF = new NavgationFragment();
        fragList.add(homeF);
        fragList.add(keowledgeF);
        fragList.add(navgationF);
        fragList.add(projectF);
        trans = getSupportFragmentManager().beginTransaction();
        trans.add(R.id.frame_group, homeF).show(homeF).commit();
        setFrament(0, getResources().getString(R.string.home_text));
    }

    public void setFrament(int position, String text) {
        title.setText(text);
        if (mPosition == position) {
            return;
        }
        FragmentTransaction transation = getSupportFragmentManager().beginTransaction();
        transation.hide(fragList.get(mPosition));
        if (!fragList.get(position).isAdded()) {
            transation.add(R.id.frame_group, fragList.get(position)).show(fragList.get(position));
        } else {
            transation.show(fragList.get(position));
        }
        transation.commit();
        mPosition = position;
    }


    @OnClick({R.id.search_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_icon:
                DialogSearchFragment searchFragment = new DialogSearchFragment();
                searchFragment.setStyle(R.style.DialogStyle, DialogFragment.STYLE_NO_TITLE);
                searchFragment.show(getSupportFragmentManager(), "SearchDialogFragment");
                break;

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tab_main_pager:
                setFrament(0, getResources().getString(R.string.home_text));
                break;
            case R.id.tab_knowledge_hierarchy:
                setFrament(1, getResources().getString(R.string.knowledge));
                break;
            case R.id.tab_navigation:
                setFrament(2, getResources().getString(R.string.nagitation));
                break;
            case R.id.tab_project:
                setFrament(3, getResources().getString(R.string.project));
                break;
        }
        return true;
    }
}
