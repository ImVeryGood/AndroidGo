package com.newdicooker.tempetek.androidgo;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.newdicooker.tempetek.androidgo.com.all.base.BaseActivity;
import com.newdicooker.tempetek.androidgo.com.all.ui.fragment.HomeFragment;
import com.newdicooker.tempetek.androidgo.com.all.ui.fragment.KeowledgeFragment;
import com.newdicooker.tempetek.androidgo.com.all.ui.fragment.NavgationFragment;
import com.newdicooker.tempetek.androidgo.com.all.ui.fragment.ProjectFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.frame_group)
    FrameLayout frameGroup;
    @BindView(R.id.nested_scroll)
    NestedScrollView nestedScroll;
    @BindView(R.id.bottom_group)
    RadioGroup bottomGroup;
    private Boolean isBottomShow = true;
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
        bottomGroup.setOnCheckedChangeListener(this);
        nestedScroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //上滑 并且 正在显示底部栏
                if (scrollY - oldScrollY > 0 && isBottomShow) {
                    isBottomShow = false;
                    //将Y属性变为底部栏高度  (相当于隐藏了)
                    bottomGroup.animate().translationY(bottomGroup.getHeight());
                } else if (scrollY - oldScrollY < 0 && !isBottomShow) {
                    isBottomShow = true;
                    bottomGroup.animate().translationY(0);
                }
            }
        });
    }


    private void setBottomNagiation() {

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
        transation.hide(fragList.get(position));
        if (!fragList.get(position).isAdded()) {
            transation.add(R.id.frame_group, fragList.get(position)).show(fragList.get(position));
        } else {
            transation.show(fragList.get(position));
        }
        transation.commit();
        mPosition = position;
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i) {
            case R.id.home_btn:
                setFrament(0, getResources().getString(R.string.home_text));
                break;
            case R.id.knowledge_btn:
                setFrament(1, getResources().getString(R.string.knowledge));
                break;
            case R.id.navigation_btn:
                setFrament(2, getResources().getString(R.string.nagitation));
                break;
            case R.id.project_btn:
                setFrament(3, getResources().getString(R.string.project));
                break;
        }
    }
}
