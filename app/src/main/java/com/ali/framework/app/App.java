package com.ali.framework.app;

import android.app.Application;

public class App extends Application {
    //全局上下文
    private static App sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static App getAppContext() {
        return sContext;
    }
}
