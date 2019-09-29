package com.roacult.kero.team7.backdropapp.controler;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import com.roacult.kero.team7.backdropapp.R;

public class SplashActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //Sets the layout of welcome_screen.xml
        setContentView(R.layout.activity_splash);
        Thread timer= new Thread()
        {
            public void run()
            {
                try
                {
                    //Display for 3 seconds
                    sleep(2000);
                }
                catch (InterruptedException e)
                {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                finally
                {
                    //Goes to Activity  StartingPoint.java(STARTINGPOINT)
                    Intent openstartingpoint=new Intent(SplashActivity.this ,HomeSearchActivity.class);
                    startActivity(openstartingpoint);
                }
            }
        };
        timer.start();
    }


    //Destroy Welcome_screen.java after it goes to next activity
    @Override
    protected void onPause()
    {
        // TODO Auto-generated method stub
        super.onPause();
        finish();


    }}
