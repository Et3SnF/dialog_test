package com.ngynstvn.android.dialogtest.application;

import android.app.Application;
import android.util.Log;

public class DialogApplication extends Application {

    private static final String TAG = "Test (" + DialogApplication.class.getSimpleName() + "): ";

    private static DialogApplication sharedInstance;

    public static DialogApplication getSharedInstance() {
        return sharedInstance;
    }

    @Override
    public void onCreate() {
        Log.v(TAG, "onCreate() called");
        super.onCreate();
        sharedInstance = this;
    }
}
