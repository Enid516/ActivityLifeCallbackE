package com.example.activitylifecallbacke;

import android.app.Activity;
import android.app.Application;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Enid on 2017/5/9.
 */

public class MyActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks{
private static final String TAG = "MyActivityLifecycleCallbacks";
    @Override
    public void onActivityCreated(final Activity activity, Bundle savedInstanceState) {
        Log.d(TAG,getActivityName(activity) + ": onActivityCreated");
        //全局设置Activity的toolbar和title
        //设置代替BaseActivity中的操作
        ActivityManager.getInstance().addActivity(activity);
        if (activity.findViewById(R.id.toolbar) != null) {
            if (activity instanceof AppCompatActivity) {
                ((AppCompatActivity) activity).setSupportActionBar((Toolbar) activity.findViewById(R.id.toolbar));
                ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    activity.setActionBar((android.widget.Toolbar) activity.findViewById(R.id.toolbar));
                    activity.getActionBar().setDisplayShowTitleEnabled(false);
                }
            }
        }

        //设置标题
        if (activity.findViewById(R.id.toolbar_title) != null) {
            ((TextView)activity.findViewById(R.id.toolbar_title)).setText(activity.getTitle());
            ((TextView)activity.findViewById(R.id.toolbar_title)).setTextColor(Color.WHITE);
        }
        //设置返回反扭点击事件
        if (activity.findViewById(R.id.toolbar_back) != null) {
            activity.findViewById(R.id.toolbar_back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onBackPressed();
                }
            });
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.d(TAG,getActivityName(activity) + ": onActivityStarted");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.d(TAG,getActivityName(activity) + ": onActivityResumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.d(TAG,getActivityName(activity) + ": onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.d(TAG,getActivityName(activity) + ": onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Log.d(TAG,getActivityName(activity) + ": onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.d(TAG,getActivityName(activity) + ": onActivityDestroyed");
        ActivityManager.getInstance().removeActivity(activity);
    }

    private String getActivityName(Activity activity){
        return activity.toString();
    }
}
