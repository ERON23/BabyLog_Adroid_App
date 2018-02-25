package com.example.maceo.babylog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class Tab1 extends Fragment {
    Button mFeedingButton;
    Button mSleepButton;
    Button mDiaperChangeButton;
    Button mChartButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);


        mFeedingButton = (Button)view.findViewById(R.id.feeding_button);
        mSleepButton = (Button) view.findViewById(R.id.sleep_button);
        mDiaperChangeButton = (Button)view.findViewById(R.id.diaper_change_button);
        mChartButton = (Button) view.findViewById(R.id.chart_button);

        mFeedingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(getActivity(),FeedingActivity.class);
                startActivity(x);
            }
        });

        mSleepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(getActivity(),SleepActivity.class);
                startActivity(x);
            }
        });

        mDiaperChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(getActivity(),DiaperChangeActivity.class);
                startActivity(x);
            }
        });

        mChartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(getActivity(),ChartActivity.class);
                startActivity(x);
            }
        });

        return view;
    }
}
