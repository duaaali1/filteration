package com.roacult.kero.team7.backdropapp.controler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.roacult.kero.team7.backdropapp.R;
import com.roacult.kero.team7.backdropapp.utils.Utils;


public class SplashActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //Sets the layout of welcome_screen.xml
        setContentView(R.layout.activity_splash);
        Thread timer = new Thread() {
            public void run() {
                try {
                   Utils. startService(SplashActivity.this);
                    sleep(2000);

                } catch (InterruptedException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                } finally {
                    Intent openstartingpoint = new Intent(SplashActivity.this, HomeSearchActivity.class);
                    startActivity(openstartingpoint);
                }
            }
        };
        timer.start();
    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();

    }

}
