package com.softdesign.skillbranch.iceandfirepedia.ui.activities;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.softdesign.skillbranch.iceandfirepedia.R;
import com.softdesign.skillbranch.iceandfirepedia.utils.ConstantManager;


public class BaseActivity extends AppCompatActivity {
    public final String TAG= ConstantManager.TAG_PREFIX+"BaseActivity";
    protected ProgressDialog mProgressDialog;
    public void showProgress(){
        if(mProgressDialog==null)
            mProgressDialog = ProgressDialog.show(this, getString(R.string.progress_dialog_up), getString(R.string.progress_dialog_mid), true, false);
            mProgressDialog.show();


    }
    public void hideProgress(){
        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mProgressDialog !=null){
                    mProgressDialog.hide();
                }
            }
        }, ConstantManager.RUN_DELAY);

    }
    public void showError(String message, Exception error){
        showToast(message);
        Log.e(TAG, String.valueOf(error));

    }
    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    public void showSnackbar(String message){
        Snackbar.make(((ViewGroup)findViewById(android.R.id.content)).getChildAt(0),message,Snackbar.LENGTH_LONG).show();
    }

}
