package com.example.maceo.babylog;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // TODO: Declare member variables here:
    Button mFeedingButton;
    Button mSleepButton;
    Button mDiaperChangeButton;
    Button mChartButtton;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout myDrawer = (DrawerLayout) findViewById(R.id.myDrawer);
        ActionBarDrawerToggle myToggle = new ActionBarDrawerToggle(this,myDrawer,toolbar,R.string.open,R.string.close);

        myDrawer.addDrawerListener(myToggle);
        myToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // TODO: casting we converted a view object to a button object
        mFeedingButton = (Button) findViewById(R.id.feeding_button);
        mSleepButton = (Button) findViewById(R.id.sleep_button);
        mDiaperChangeButton = (Button) findViewById(R.id.diaper_change_button);
        mChartButtton = (Button) findViewById(R.id.chart_button);

        fab = (FloatingActionButton)findViewById(R.id.fab1);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(HomeActivity.this, "Fab Clicked", Toast.LENGTH_LONG).show();

            }
        });

        // TODO: set OnClick listener to change screen when button is pressed
        //example
       /* mBoyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent r = new Intent(WelcomeActivity.this,BabyInfoActivity.class);
                startActivity(r);
            }
        });*/

       mFeedingButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent x = new Intent(HomeActivity.this,FeedingActivity.class);
               startActivity(x);
           }
       });

        mSleepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(HomeActivity.this,SleepActivity.class);
                startActivity(x);
            }
        });

        mDiaperChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(HomeActivity.this,DiaperChangeActivity.class);
                startActivity(x);
            }
        });

        mChartButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(HomeActivity.this,ChartActivity.class);
                startActivity(x);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.myDrawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.feed) {
            startActivity(new Intent(this,FeedingActivity.class));

        } else if (id == R.id.diaper) {
            startActivity(new Intent(this,DiaperChangeActivity.class));

        } else if (id == R.id.sleep) {
            startActivity(new Intent(this,SleepActivity.class));

        } else if (id == R.id.chart) {
            startActivity(new Intent(this,ChartActivity.class));

        } else if (id == R.id.journal) {
//            startActivity(new Intent(this,.class));

        } else if (id == R.id.tt) {
//            startActivity(new Intent(this,.class));

        } else if (id == R.id.weightT) {
//            startActivity(new Intent(this,.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.myDrawer);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


}
