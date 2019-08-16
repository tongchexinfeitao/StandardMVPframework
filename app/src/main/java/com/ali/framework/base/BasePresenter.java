package com.ali.framework.base;

import android.content.Context;

import com.ali.framework.app.App;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends IBaseView> {

    public BasePresenter() {
        //初始化model
        initModel();
    }

    /**
     * 初始化model
     */
    protected abstract void initModel();

    private WeakReference<V> mWeakReference;

    protected void attachView(V v) {
        mWeakReference = new WeakReference<>(v);
    }

    protected void detachView() {
        if (mWeakReference != null) {
            mWeakReference.clear();
            mWeakReference = null;
        }
    }

    /**
     * 判断view层是否挂载
     *
     * 防止出现 presenter 层 view 调用空指针
     * 每次调用业务请求的时候都要先调用方法检查是否与 View 绑定
     * 只有返回ture才进行回调
     */
    protected boolean isViewAttached() {
        if (mWeakReference == null || mWeakReference.get() == null) {
            return false;
        }
        return true;
    }

    /**
     * 获取view接口
     *
     * 每只有 isViewAttached 返回ture的时候，才能调用他
     */
    protected V getView() {
        return mWeakReference.get();
    }

    /**
     * context为上下文
     *
     * 上下文为null，则返回应用上下文
     */
    protected Context context() {
        if (isViewAttached() && getView().context() != null) {
            return getView().context();
        }
        return App.getAppContext();
    }
}
