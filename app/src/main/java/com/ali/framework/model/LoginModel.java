package com.ali.framework.model;

import com.ali.framework.contract.ILoginContract;
import com.ali.framework.model.api.ILoginApi;
import com.ali.framework.model.bean.LoginBean;
import com.ali.framework.utils.RetrofitManager;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginModel implements ILoginContract.IModel {

    @Override
    public void login(Map<String, Object> paramsMap, final IModelCallback callback) {
        RetrofitManager.getInstance().create(ILoginApi.class)
                .login(paramsMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        callback.onLoginSuccess(loginBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onLoginFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
