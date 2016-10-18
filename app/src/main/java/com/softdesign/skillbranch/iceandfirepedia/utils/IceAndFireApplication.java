package com.softdesign.skillbranch.iceandfirepedia.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.facebook.stetho.Stetho;
import com.softdesign.skillbranch.iceandfirepedia.data.storage.models.DaoMaster;
import com.softdesign.skillbranch.iceandfirepedia.data.storage.models.DaoSession;

import org.greenrobot.greendao.database.Database;

public class IceAndFireApplication extends Application {
    public static SharedPreferences sSharedPreferences;
    private static Context sContext;
    private static DaoSession sDaoSession;

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        sContext = this;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "iceandfire = db");
        Database db = helper.getWritableDb();
        sDaoSession = new DaoMaster(db).newSession();

        Stetho.initializeWithDefaults(this);

    }

    public static DaoSession getDaoSession() {
        return sDaoSession;
    }

    public static SharedPreferences getSharedPreferences() {
        return sSharedPreferences;
    }
}