package com.ali.framework.utils;

import com.ali.framework.app.Constant;
import com.ali.framework.model.api.IApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit工具类
 *
 * 使用规范：
 * 1、{@link RetrofitManager}使用静态内部类的单例模式
 * 2、使用 {@link #getInstance()}方法获取单例
 * 3、使用单例的{@link #create()}方法获取默认的{@link IApi}对象
 * 4、使用获取到的IApi对象调用对应的接口方法
 */
public class RetrofitManager {
    private Retrofit mRetrofit;
    private static final String BASE_URL = Constant.BASE_URL;
    //默认的IApi
    private IApi mIApi;

    private static final class SingleHolder {
        private static final RetrofitManager _INSTANCE = new RetrofitManager(BASE_URL);
    }

    public static RetrofitManager getInstance() {
        return SingleHolder._INSTANCE;
    }

    private RetrofitManager(String url) {
        mRetrofit = new Retrofit.Builder()
                //添加自动gson解析
                .addConverterFactory(GsonConverterFactory.create())
                //让Retrofit支持RxJava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //设置公共的url
                .baseUrl(url)
                //配置自己的okhttpClinet
                .client(buildOkhttpClient())
                .build();
    }

    private OkHttpClient buildOkhttpClient() {
        //日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        //设置日志拦截器打印日志的级别
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //构造一个OkHttpClient对应
        return new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    /**
     * 如果有其他的IApi，通过这个有参的方法创建
     */
    public <T> T create(final Class<T> service) {
        return mRetrofit.create(service);
    }

    /**
     * 创建默认的单例IApi
     */
    public IApi create() {
        if (mIApi == null) {
            mIApi = create(IApi.class);
        }
        return mIApi;
    }

}
