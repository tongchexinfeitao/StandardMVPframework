package com.ali.framework.utils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 使用规范：
 * 使用 {@link CommonObserver} 代替 Observer，
 * 可以避免创建 {@link Observer} 对象强制重写 onSubscribe() 和 onComplete()方法，简化代码
 */
public abstract class CommonObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }
}
