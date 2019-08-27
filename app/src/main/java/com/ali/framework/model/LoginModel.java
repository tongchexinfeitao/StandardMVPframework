package com.ali.framework.model;

import com.ali.framework.contract.ILoginContract;
import com.ali.framework.model.bean.LoginBean;
import com.ali.framework.utils.CommonObserver;
import com.ali.framework.utils.CommonSchedulers;
import com.ali.framework.utils.RetrofitManager;

import java.util.Map;


/**
 * 声明规范：
 * 1、命名规范为 "${业务模块}Model"  如：登录模块 Model 命名为 "LoginModel"
 * 2、必须 implements 对应锲约中的IModel接口 如: {@link ILoginContract.IModel}
 * <p>
 * <p>
 * 使用规范：
 * 1、使用 {@link RetrofitManager} 类的 create() 方法构造默认的IApi
 * 2、使用 compose()配合 {@link CommonSchedulers} 切换线程
 * 3、使用 {@link CommonObserver} 代替 {@link io.reactivex.Observer} ,避免每次强制重写 onSubscribe() 和 onComplete()
 */
public class LoginModel implements ILoginContract.IModel {
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
