package com.example.maceo.babylog;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by eortxz on 3/17/18.
 */

public class TummyTimeActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 600000;

    private TextView countdownText;
    private Button countdownbutton;
    private Button mButtonReset;

    private CountDownTimer countDownTimer;
    private long timeleftInMilliseconds=600000;// 10 mins
    private boolean timerRunning;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_tummytime);

        countdownText = findViewById(R.id.countdown_text);
        countdownbutton = findViewById(R.id.countdown_button);
        mButtonReset = findViewById(R.id.button_reset);
        
        countdownbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStop();
            }
        });
        updateTimer();
    }
    public void startStop(){
        if (timerRunning){
            stopTimer();
        }else{
            startTimer();
        }
    }
    public void startTimer(){
        countDownTimer =new CountDownTimer(timeleftInMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeleftInMilliseconds =l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        countdownbutton.setText("Pause");
        timerRunning = true;
    }
    public void stopTimer(){
        countDownTimer.cancel();
        countdownbutton.setText("Start");
        timerRunning = false;
    }
    public void updateTimer(){
        int minutes = (int) timeleftInMilliseconds /60000;
        int seconds = (int) timeleftInMilliseconds % 60000 /1000;

        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        countdownText.setText(timeLeftText);
    }
}