package com.zt.recyclerview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.zt.recyclerview.R;

public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_TIME = 2 * 1000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);

    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_TIME);
    }
}


