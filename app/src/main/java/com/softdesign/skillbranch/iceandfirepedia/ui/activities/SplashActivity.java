package com.softdesign.skillbranch.iceandfirepedia.ui.activities;

import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.v4.net.ConnectivityManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.softdesign.skillbranch.iceandfirepedia.R;
import com.softdesign.skillbranch.iceandfirepedia.data.managers.DataManager;
import com.softdesign.skillbranch.iceandfirepedia.utils.ConstantManager;
import com.softdesign.skillbranch.iceandfirepedia.utils.NetworkStatusChecker;

public class SplashActivity extends BaseActivity {

    private static final String TAG = "Splash Activity";
    DataManager mDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        showProgress();
        setTheme(R.style.AppThemeBefore);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mDataManager = DataManager.getInstance();
        if (!mDataManager.getPreferencesManager().isDbComplete()) {
            mDataManager.getPreferencesManager().setDbComplete(true);
            if (NetworkStatusChecker.isNetworkAvailable(this)) {
                mDataManager.saveBooksToDb();
                for (Long aLong : ConstantManager.HOUSES_ID) {
                    mDataManager.saveHouseToDb(aLong);
                }
            }
        }
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mDataManager.getPreferencesManager().isDbComplete()) {
                    Intent toMainActivity = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(toMainActivity);
                } else {
                    showToast(getString(R.string.db_not_loaded));
                }
//                hideProgress();
            }
        }, ConstantManager.DELAY);


    }


}
