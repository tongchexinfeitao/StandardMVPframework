package com.ali.framework.model.api;


import com.ali.framework.model.bean.LoginBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Retrofit默认只使用这一个IApi
 * <p>
 * <p>
 * 使用规范：
 * 一个http请求对应该接口中一个方法
 */
public interface IApi {

    @FormUrlEncoded
    @POST("user/v1/login")
    Observable<LoginBean> login(@FieldMap Map<String, Object> map);
}
