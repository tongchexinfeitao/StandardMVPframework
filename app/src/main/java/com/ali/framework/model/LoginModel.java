package com.ali.framework.model;

import android.annotation.SuppressLint;

import com.ali.framework.contract.ILoginContract;
import com.ali.framework.model.api.ILoginApi;
import com.ali.framework.model.bean.LoginBean;
import com.ali.framework.utils.RetrofitManager;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginModel implements ILoginContract.IModel {


    @SuppressLint("CheckResult")
    @Override
    public void login(Map<String, Object> paramsMap, final IModelCallback callback) {
        RetrofitManager.getInstance().create(ILoginApi.class)
                .login(paramsMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        callback.onLoginSuccess(loginBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onLoginFailure(throwable);
                    }
                });

    }
}
