package com.ali.framework.presenter;

import com.ali.framework.app.Constant;
import com.ali.framework.base.BasePresenter;
import com.ali.framework.contract.ILoginContract;
import com.ali.framework.model.LoginModel;
import com.ali.framework.model.bean.LoginBean;

import java.util.Map;

/**
 * 声明规范：
 * 1、命名规范为 "${业务模块}Presenter"  如：登录模块 Presenter 命名为 "LoginPresenter"
 * 2、必须指定泛型 V 的具体类型
 * 3、必须 implements 对应锲约中的 IPresenter 接口  如: {@link ILoginContract.IPresenter}
 * <p>
 * <p>
 * 使用规范：
 * 1、在 {@link #initModel()}方法中初始化Model
 * 2、调用 Model 中的的方法，并设置 {@link ILoginContract.IModel.IModelCallback}回调监听
 * 3、成功失败处，先调用 {@link #isViewAttached()} 判断是否挂载、然后才可以使用{@link #getView()}方法获取view，进行数据回调
 */
public class LoginPresenter extends BasePresenter<ILoginContract.IView> implements ILoginContract.IPresenter {

    private LoginModel mLoginModel;

    @Override
    public void login(Map<String, Object> map) {
        //2、调用model中的的方法，设置回调监听
        mLoginModel.login(map, new ILoginContract.IModel.IModelCallback() {
            @Override
            public void onLoginSuccess(LoginBean loginBean) {
                //3、必须先判断是否挂载、然后才可以使用getView方法
                if (isViewAttached()) {
                    if (loginBean != null && Constant.SUCCESS_CODE.equals(loginBean.getStatus())) {
                        getView().onLoginSuccess(loginBean);
                    } else {
                        getView().onLoginFailure(new Exception("服务器异常"));
                    }
                }
            }

            @Override
            public void onLoginFailure(Throwable e) {
                //4、失败回调
                if (isViewAttached()) {
                    getView().onLoginFailure(e);
                }
            }
        });
    }

    /**
     * 1、在这个方法中初始化model
     */
    @Override
    protected void initModel() {
        mLoginModel = new LoginModel();
    }
}
