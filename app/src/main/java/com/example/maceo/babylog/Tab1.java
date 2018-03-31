package com.example.maceo.babylog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Tab1 extends Fragment {
    Button mFeedingButton, mSleepButton, mDiaperChangeButton, mChartButton, mTummyTime, mWeightTracking;

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
        mTummyTime = (Button) view.findViewById(R.id.tummytime_button);
        mWeightTracking = (Button) view.findViewById(R.id.weight_tracker_button);

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
        mWeightTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(getActivity(), WeighyTrackingActivity.class);
                startActivity(x);
                                            }
                                        });
        mTummyTime.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* In here put the java class you want it to go to*/
                Intent x = new Intent(getActivity(),TummyTimeActivity.class);
                startActivity(x);
            }
        }));

        return view;
    }
}
