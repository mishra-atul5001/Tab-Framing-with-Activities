package com.example.mishr.tabbingfragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_splash_screen);
      //  Toast.makeText(SplashScreenActivity.this,"This ia a Splash Screen", Toast.LENGTH_SHORT).show();
        for(int i = 0 ; i<2000;i++){
            //Delaying the Splash Screen
        }
        Thread timer=new Thread()
        {
            public void run() {
                try {
                    sleep(2000);

                } catch (InterruptedException e) {
                    //Just for making splash screen stay longer
                    e.printStackTrace();
                }
                finally
                {
                    Intent i=new Intent(SplashScreenActivity.this,MainActivity.class);
                    finish();
                    startActivity(i);
                }

            }
        };
        timer.start();
        // Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
        //  startActivity(intent);
    }

    }

