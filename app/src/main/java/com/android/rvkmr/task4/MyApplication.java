package com.android.rvkmr.task4;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by rvkmr on 10-03-2018.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
