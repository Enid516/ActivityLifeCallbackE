package com.example.activitylifecallbacke;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enid on 2017/5/9.
 */

public class ActivityManager {
    private static final String TAG = "ActivityManager";
    private List<Activity> activities = new ArrayList<>();

    private static ActivityManager INSTANCE;

    private ActivityManager() {
    }

    public static ActivityManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ActivityManager();
        }
        return INSTANCE;
    }

    public void addActivity(Activity activity) {
        Log.i(TAG,"addActivity: " + activity.getClass().getName());
        activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        Log.i(TAG,"removeActivity: " + activity.getClass().getName());
        activity.finish();
        activities.remove(activity);
    }

    public void removeAllActivity() {
        for (Activity activity :
                activities) {
            removeActivity(activity);
        }
    }
}
