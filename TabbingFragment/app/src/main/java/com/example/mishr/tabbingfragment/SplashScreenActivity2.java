package com.example.mishr.tabbingfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by mishr on 14/09/2017.
 */

public class SplashScreenActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(SplashScreenActivity2.this,"This ia a Splash Screen", Toast.LENGTH_SHORT).show();
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
                    Intent i=new Intent(SplashScreenActivity2.this,SecondActivity.class);
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

