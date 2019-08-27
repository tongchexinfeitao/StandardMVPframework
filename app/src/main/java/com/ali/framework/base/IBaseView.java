package com.ali.framework.base;

import android.content.Context;

/**
 * P 层回调 V 层的基类接口
 * ps：
 * 所有用于 P 和 V 数据回调的接口都应该继承该接口
 */
public interface IBaseView {
    /**
     * 为presenter层提供上下文   ps： 非必须
     */
    Context context();
}
