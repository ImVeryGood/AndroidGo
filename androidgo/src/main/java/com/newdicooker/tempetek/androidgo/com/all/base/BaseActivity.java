package com.newdicooker.tempetek.androidgo.com.all.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.android.tu.loadingdialog.LoadingDailog;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/*activity基类*/
public abstract class BaseActivity extends AppCompatActivity {
    protected ImmersionBar mImmersionBar;
    private Unbinder unbinder;
    private InputMethodManager imm;
    private LoadingDailog.Builder builder;
    protected LoadingDailog loadingDailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        unbinder = ButterKnife.bind(this);
        setImmerBar();
        setLoading();
        initView();
        initData();
        initListener();

    }


    /**
     * 布局id
     *
     * @return
     */
    protected abstract int setLayoutId();

    /**
     * view的操作
     */
    protected abstract void initView();

    /**
     * date的操作
     */
    protected abstract void initData();

    /**
     * 监听
     */
    protected abstract void initListener();


    protected void setLogin() {

    }

    protected void setLoading() {
        builder = new LoadingDailog.Builder(this)
                .setMessage("加载中")
                .setCancelable(true);
        loadingDailog = builder.create();
    }

    public void setImmerBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.imm = null;
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
        unbinder.unbind();
    }

    @Override
    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }

    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }
}
