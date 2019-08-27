package com.ali.framework.view.activity;

import android.util.Log;
import android.widget.Toast;

import com.ali.framework.R;
import com.ali.framework.base.BaseActivity;
import com.ali.framework.contract.ILoginContract;
import com.ali.framework.model.bean.LoginBean;
import com.ali.framework.presenter.LoginPresenter;

import java.util.HashMap;

import butterknife.OnClick;

/**
 * 声明规范：
 * 1、命名规范为 "${业务模块}Activity"  如：登录模块 Activity 命名为 "LoginActivity"
 * 2、必须指定泛型 P 的具体类型
 * 3、必须 implements 对应锲约中的 IView 接口  如: {@link ILoginContract.IView}
 * <p>
 * <p>
 * 使用规范：
 * 1、必须在 {@link #provideLayoutId()} 方法中返回对应的 laytoutId
 * 2、必须在 {@link #providePresenter()} 方法中返回对应的 Presenter 对象
 * 3、在{@link #initData()}中联网请求
 * 4、在重写的成功、失败方法中，更新UI
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginContract.IView {


    @Override
    protected LoginPresenter providePresenter() {
        return new LoginPresenter();
    }


    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    //成功
    @Override
    public void onLoginSuccess(LoginBean loginBean) {
        Log.i("TAG", "登录成功: " + loginBean.toString());
        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    //失敗
    @Override
    public void onLoginFailure(Throwable e) {
        Log.i("TAG", "登录失败: " + e.toString());
        Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        //去登录
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("phone", "15501186660");
        paramsMap.put("pwd", "123456");
        mPresenter.login(paramsMap);
    }
}
