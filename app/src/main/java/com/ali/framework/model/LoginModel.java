package com.ali.framework.model;

import com.ali.framework.contract.ILoginContract;
import com.ali.framework.model.bean.LoginBean;
import com.ali.framework.utils.CommonObserver;
import com.ali.framework.utils.CommonSchedulers;
import com.ali.framework.utils.RetrofitManager;

import java.util.Map;

public class LoginModel implements ILoginContract.IModel {


    /**
     * 1、create()方法构造默认的IApi
     * 2、使用 compose()和 CommonSchedulers 配合切换线程
     * 3、使用CommonObserver,避免每次重写 onSubscribe（） 和 onComplete()
     */
    @Override
    public void login(Map<String, Object> paramsMap, final IModelCallback callback) {
        RetrofitManager.getInstance().create()
                .login(paramsMap)
                .compose(CommonSchedulers.<LoginBean>io2main())
                .subscribe(new CommonObserver<LoginBean>() {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        callback.onLoginSuccess(loginBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onLoginFailure(e);
                    }
                });
    }
}
