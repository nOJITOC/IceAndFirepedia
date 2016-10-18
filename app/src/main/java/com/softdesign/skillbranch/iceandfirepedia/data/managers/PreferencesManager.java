package com.softdesign.skillbranch.iceandfirepedia.data.managers;

import android.content.SharedPreferences;

import com.softdesign.skillbranch.iceandfirepedia.utils.ConstantManager;
import com.softdesign.skillbranch.iceandfirepedia.utils.IceAndFireApplication;

/**
 * Created by Иван on 15.10.2016.
 */
public class PreferencesManager {
    private SharedPreferences mSharedPreferences;
    public PreferencesManager(){
        mSharedPreferences = IceAndFireApplication.getSharedPreferences();
    }
    public boolean isDbComplete(){
        return mSharedPreferences.getBoolean(ConstantManager.DB_COMPLETE,false);
    }
    public void setDbComplete(boolean cond){
        SharedPreferences.Editor editor =  mSharedPreferences.edit();
        editor.putBoolean(ConstantManager.DB_COMPLETE,cond);
        editor.apply();
    }
}
