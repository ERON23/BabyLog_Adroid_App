package com.example.maceo.babylog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nex3z.notificationbadge.NotificationBadge;


public class Tab2 extends Fragment {
/*
    Button mIncrease, mDecrease, mClear;
    NotificationBadge mbadge;
    int count = 0;
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);

        /*mIncrease = (Button)view.findViewById(R.id.add_btn);
        mDecrease = (Button)view.findViewById(R.id.less_btn);
        mClear = (Button)view.findViewById(R.id.clear_btn);
        mbadge = (NotificationBadge)view.findViewById(R.id.notification_badge);*/

        /*mIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mbadge.setNumber(count++);
            }
        });
        mDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mbadge.setNumber(count--);
            }
        });
        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mbadge.setNumber(0);
            }
        });*/



        return view;
    }

}
