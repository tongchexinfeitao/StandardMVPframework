package com.ali.framework.utils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 使用BaseObserver，可以避免重写强制重写onSubscribe和onComplete
 *
 * @param <T>
 */
public abstract class CommonObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }
}
