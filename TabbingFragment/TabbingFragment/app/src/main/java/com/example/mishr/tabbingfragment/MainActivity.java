package com.example.mishr.tabbingfragment;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button sports,music,world;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sports = (Button)findViewById(R.id.cardbut1);
        music = (Button)findViewById(R.id.cardbut2);
        world = (Button)findViewById(R.id.cardbut3);


        sports.setOnClickListener(this);
        music.setOnClickListener(this);
        world.setOnClickListener(this);
      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);

        setSupportActionBar(toolbar);
        //Your toolbar is now an action bar and you can use it like you always do, for example:
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                 */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cardbut1:
                SportsFragment sportsFragment = new SportsFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.layoutreplacedtobe,sportsFragment,sportsFragment.getTag()).commit();

                break;

            case R.id.cardbut2:
                MusicFragment musicFragment = new MusicFragment();
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                fragmentManager1.beginTransaction().replace(R.id.layoutreplacedtobe,musicFragment,musicFragment.getTag()).commit();


                break;

            case R.id.cardbut3:
                WorldFragment worldFragment = new WorldFragment();
                FragmentManager fragmentManager2 = getSupportFragmentManager();
                fragmentManager2.beginTransaction().replace(R.id.layoutreplacedtobe,worldFragment,worldFragment.getTag()).commit();

                break;


        }

    }

    public void nextactivity(View view) {
        Intent intent =new Intent(this,SecondActivity.class);
        startActivity(intent);
    }

    public void gobacktomainactivity(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        setVisible(true);
    }
}
