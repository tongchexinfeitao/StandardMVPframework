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
 * 需要指定 泛型 P 的具体类型
 * 需要实现对应锲约的IView接口
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
