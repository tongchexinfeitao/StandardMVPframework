package com.ali.framework.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ali.framework.utils.NetUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    protected P mPresenter;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayoutId());
        mUnbinder = ButterKnife.bind(this);
        mPresenter = providePresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initView();
        initData();
    }

    protected abstract P providePresenter();

    /**
     * 空实现，子类需要用的时候，可以选择重写
     */
    protected void initData() {
    }

    /**
     * 空实现，子类需要用的时候，可以选择重写
     */
    protected void initView() {
    }


    protected abstract int provideLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    /**
     * 判断是否有网络
     */
    public boolean hasNetwork() {
        return NetUtil.hasNetwork(this);
    }

    /**
     * 无网提醒
     */
    public void showNoNetTip() {
        Toast.makeText(BaseActivity.this, "无网，请检查网络", Toast.LENGTH_SHORT).show();
    }

    /**
     * 为presenter层提供上下文环境，ps： 非必须
     */
    @Override
    public Context context() {
        return this;
    }
}
