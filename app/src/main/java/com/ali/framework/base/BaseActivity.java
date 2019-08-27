package com.ali.framework.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ali.framework.utils.NetUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * BaseActivity声明规范：
 * 1、封装泛型 P extents {@link BasePresenter}
 * 2、implements {@link IBaseView}  ps:方便基类封装 P 层绑定和解绑 view 的操作
 * 3、使用模板方法设计模式，规范子类执行流程
 * 4、封装 {@link ButterKnife} bind 和 unBind
 * 5、封装 P 层 attach 和 dettach
 * 6、{@link #initView()} ()} 方法空实现，必要的时候子类可以重写，进行find特殊控件，和设置特殊监听
 * 7、{@link #initData()} 方法空实现，必要的时候子类可以重写，进行初始化数据操作
 * <p>
 * <p>
 * 子类使用规范：
 * {@link com.ali.framework.view.activity.LoginActivity}
 */
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
