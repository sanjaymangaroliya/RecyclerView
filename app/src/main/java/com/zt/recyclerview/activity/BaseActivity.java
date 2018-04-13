package com.zt.recyclerview.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;

import com.zt.recyclerview.R;
import com.zt.recyclerview.global.GlobalApp;
import com.zt.recyclerview.global.Utils;

public class BaseActivity extends AppCompatActivity {

    protected SharedPreferences pref;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        pref = GlobalApp.getPref(this);
    }

    protected String getData(String strKey) {
        return pref.getString(strKey, "");
    }

    protected void setData(String strKey, String strValue) {
        pref.edit().putString(strKey, strValue).commit();
    }

    protected void saveData(String key, String value) {
        pref.edit().putString(key, value).commit();
    }

   /* @Override
    protected void onStart() {
        super.onStart();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null)
            setSupportActionBar(toolbar);
       *//* if (findViewById(R.id.relmain) != null) {
            findViewById(R.id.relmain).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hideSoftKeyboard();
                }
            });
        }*//*
    }*/

    protected void onFailedResponse(int statusCode, String msg) {
        if (statusCode == 100) {
            //internetErrorDialog();
            return;
        }
        Utils.showToast(this, msg);
        if (msg.equals(getResources().getString(R.string.app_name))) {
            doLogout();
        }
    }

    protected void removeData(String key) {
        pref.edit().remove(key).commit();
    }

    protected void clearAllData() {
        pref.edit().clear().commit();
    }

    protected void doLogout() {
        String email = getData("email");
        clearAllData();
        saveData("email", email);
        saveData("guest", "true");
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        // close this activity and return to preview activity (if there is any)
        if (item.getItemId() == android.R.id.home) {
            Utils.hideKeyboard(this);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
