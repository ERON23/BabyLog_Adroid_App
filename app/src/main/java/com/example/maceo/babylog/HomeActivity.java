package com.example.maceo.babylog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {

    // TODO: Declare member variables here:
    Button mFeedingButton;
    Button mSleepButton;
    Button mDiaperChangeButton;
    Button mChartButtton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // TODO: casting we converted a view object to a button object
        mFeedingButton = (Button) findViewById(R.id.feeding_button);
        mSleepButton = (Button) findViewById(R.id.sleep_button);
        mDiaperChangeButton = (Button) findViewById(R.id.diaper_change_button);
        mChartButtton = (Button) findViewById(R.id.chart_button);

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
}
