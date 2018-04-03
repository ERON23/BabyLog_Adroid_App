package com.example.maceo.babylog;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.nex3z.notificationbadge.NotificationBadge;

import java.util.Map;


public class Tab2 extends Fragment {
/*
    Button mIncrease, mDecrease, mClear;
    NotificationBadge mbadge;
    int count = 0;
*/
    private TextView mLastBottleFeedingTimeStamp, mLastBFOZ,mLastBFNote;
    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);

        mLastBottleFeedingTimeStamp = (TextView) view.findViewById(R.id.last_date_and_time);
        mLastBFOZ = (TextView) view.findViewById(R.id.last_amount_in_oz);
        mLastBFNote = (TextView) view.findViewById(R.id.txt_last_bf_note);

        mAuth = FirebaseAuth.getInstance();
        String user_id = mAuth.getCurrentUser().getUid();
        final DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(mAuth.getCurrentUser().getUid())
                .child("Feeding").child("Bottle Feeding").child("Time Stamp");

        current_user_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()){
                    final String Date_and_Time = child.getKey();
                    //mLastBottleFeedingTimeStamp.setText(Date_and_Time);

                    current_user_db.child(Date_and_Time).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot child: dataSnapshot.getChildren()){
                                String time = child.getKey();
                                //String Amount_In_Oz = child.getKey();
                                mLastBottleFeedingTimeStamp.setText(Date_and_Time +" "+time);
                                //mLastBFOZ.setText(Amount_In_Oz);


                                current_user_db.child(Date_and_Time).child(time).child("Amount_In_Oz").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String Amount_In_Oz = dataSnapshot.getValue(String.class);
                                        mLastBFOZ.setText(Amount_In_Oz);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        /////hghghghghghghghghghg

                                    }
                                });

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    //System.out.print(Date_and_Time);

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        return view;









    }

}
