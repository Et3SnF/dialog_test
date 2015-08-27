package com.ngynstvn.android.dialogtest;

import android.app.Application;
import android.util.Log;

import com.ngynstvn.android.dialogtest.data.FakeData;

public class DialogApplication extends Application {

    private static final String TAG = "Test (" + DialogApplication.class.getSimpleName() + "): ";

    private static DialogApplication sharedInstance;
    private FakeData fakeData;

    @Override
    public void onCreate() {
        Log.v(TAG, "onCreate() called");
        super.onCreate();
        fakeData = new FakeData();
        sharedInstance = this;
    }

    public static DialogApplication getSharedInstance() {
        return sharedInstance;
    }

    public FakeData getFakeData() {
        return fakeData;
    }

    public static FakeData getSharedFakeData() {
        Log.v(TAG, "getSharedFakeData() called");
        return DialogApplication.getSharedInstance().getFakeData();
    }

}
