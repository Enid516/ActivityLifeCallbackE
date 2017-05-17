package com.example.activitylifecallbacke;

import android.app.Application;

/**
 * Created by Enid on 2017/5/9.
 */

public class MyApplication extends Application {
    private static MyApplication INSTANCE;

    private MyActivityLifecycleCallbacks myActivityLifecycleCallbacks;

//    private MyApplication(){}

    public static MyApplication getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new MyApplication();
        }
        return INSTANCE;
    }

    private MyActivityLifecycleCallbacks getMyActivityLifecycleCallbacks(){
        return myActivityLifecycleCallbacks;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myActivityLifecycleCallbacks = new MyActivityLifecycleCallbacks();
        registerActivityLifecycleCallbacks(myActivityLifecycleCallbacks);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterActivityLifecycleCallbacks(myActivityLifecycleCallbacks);
    }
}
