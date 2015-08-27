package com.ngynstvn.android.dialogtest.application;

import android.app.Application;
import android.util.Log;

import com.ngynstvn.android.dialogtest.data.FakeData;

public class DialogApplication extends Application {

    private static final String TAG = "Test (" + DialogApplication.class.getSimpleName() + "): ";

    private FakeData fakeData;

    private static DialogApplication sharedInstance;

    public static DialogApplication getSharedInstance() {
        return sharedInstance;
    }

    public static FakeData getSharedFakeData() {
        return DialogApplication.getSharedInstance().getFakeData();
    }

    private FakeData getFakeData() {
        return fakeData;
    }

    @Override
    public void onCreate() {
        Log.v(TAG, "onCreate() called");
        super.onCreate();
        sharedInstance = this;
        fakeData = new FakeData();
    }
}
