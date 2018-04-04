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
    private TextView mLastBreastFeedTimeStamp, mLeftBreastFeed, mRightBreastFeed, mLastBreastFeedNote;
    private TextView mLastMealFeedTimeStamp, mLastMealConsumed, mLastSupplementConsumed, mLastMealNote;
    private TextView mLastDiaperTimeStamp, mLastDiaperStatus, mLastDiaperNote;


    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);

        mLastBottleFeedingTimeStamp = view.findViewById(R.id.last_date_and_time);
        mLastBFOZ = view.findViewById(R.id.last_amount_in_oz);
        mLastBFNote = view.findViewById(R.id.txt_last_bf_note);
        mLastBreastFeedTimeStamp = (TextView) view.findViewById(R.id.last_date_and_time_breast_feed);
        mLeftBreastFeed = (TextView) view.findViewById(R.id.left_breast_feed_time);
        mRightBreastFeed = (TextView) view.findViewById(R.id.right_breast_feed_time);
        mLastBreastFeedNote = (TextView) view.findViewById(R.id.txt_note_breast_feed);
        mLastMealFeedTimeStamp = (TextView) view.findViewById(R.id.last_time_stamp_meals);
        mLastMealConsumed = (TextView) view.findViewById(R.id.last_meal_consumed);
        mLastSupplementConsumed = (TextView) view.findViewById(R.id.last_supplement_consumed);
        mLastMealNote = (TextView) view.findViewById(R.id.txt_note_meal);
        mLastDiaperTimeStamp = (TextView) view.findViewById(R.id.diaper_last_timestamp);
        mLastDiaperStatus = (TextView) view.findViewById(R.id.edt_diaper_status);
        mLastDiaperNote = (TextView) view.findViewById(R.id.edt_diaper_note);

        mAuth = FirebaseAuth.getInstance();
        String user_id = mAuth.getCurrentUser().getUid();
        final DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(mAuth.getCurrentUser().getUid())
                .child("Feeding").child("Bottle Feeding").child("Time Stamp");

        current_user_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()){
                    final String date = child.getKey();
                    //mLastBottleFeedingTimeStamp.setText(Date_and_Time);

                    current_user_db.child(date).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot child: dataSnapshot.getChildren()){
                                String time = child.getKey();
                                //String Amount_In_Oz = child.getKey();
                                mLastBottleFeedingTimeStamp.setText(date +" "+time);
                                //mLastBFOZ.setText(Amount_In_Oz);


                                current_user_db.child(date).child(time).child("Amount_In_Oz").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String Amount_In_Oz = dataSnapshot.getValue(String.class);
                                        mLastBFOZ.setText(Amount_In_Oz);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                                current_user_db.child(date).child(time).child("Bottle_Feeding_Notes").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String Bottle_Feeding_Notes = dataSnapshot.getValue(String.class);
                                        mLastBFNote.setText(Bottle_Feeding_Notes);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });



                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // end of bottle feeding

        /*
        ***********************************************************************************************************
         */

        // beginning of brest feeding
        final DatabaseReference current_user_db2 = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(mAuth.getCurrentUser().getUid())
                .child("Feeding").child("Breast Feeding").child("Time Stamp");

        current_user_db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()){
                    final String date = child.getKey();

                    current_user_db2.child(date).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot child: dataSnapshot.getChildren()){
                                String time = child.getKey();
                                mLastBreastFeedTimeStamp.setText(date + " "+ time);

                                current_user_db2.child(date).child(time).child("Left_Breast_Feeding_Time").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String Left_Breast_Feeding_Time = dataSnapshot.getValue(String.class);
                                        mLeftBreastFeed.setText(Left_Breast_Feeding_Time);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                                current_user_db2.child(date).child(time).child("Right_Breast_Feeding_Time").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String Right_Breast_Feeding_Time = dataSnapshot.getValue(String.class);
                                        mRightBreastFeed.setText(Right_Breast_Feeding_Time);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });


                                current_user_db2.child(date).child(time).child("Breast_Feeding_Note").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String Breast_Feeding_Note = dataSnapshot.getValue(String.class);
                                        mLastBreastFeedNote.setText(Breast_Feeding_Note);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });



                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //end of breast feeding

        /*
        ***********************************************************************************************************
         */

        // beginning of meal feeding
        final DatabaseReference current_user_db3 = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(mAuth.getCurrentUser().getUid())
                .child("Feeding").child("Meal Feeding").child("Time Stamp");

        current_user_db3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()){
                    final String date = child.getKey();

                    current_user_db3.child(date).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot child: dataSnapshot.getChildren()){
                                String time = child.getKey();
                                mLastMealFeedTimeStamp.setText(date + " "+ time);

                                current_user_db3.child(date).child(time).child("Meal_Consumed").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String Meal_Consumed = dataSnapshot.getValue(String.class);
                                        mLastMealConsumed.setText(Meal_Consumed);
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });

                                current_user_db3.child(date).child(time).child("Supplement_Consumed").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String Supplement_Consumed = dataSnapshot.getValue(String.class);
                                        mLastSupplementConsumed.setText(Supplement_Consumed);
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });

                                current_user_db3.child(date).child(time).child("Meal_Note").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String Meal_Note = dataSnapshot.getValue(String.class);
                                        mLastMealNote.setText(Meal_Note);
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });

                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        //end of meal feeding

        /*
        ***********************************************************************************************************
         */

        // beginning of Diaper Status
        final DatabaseReference current_user_db4 = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(mAuth.getCurrentUser().getUid())
                .child("Diaper Status").child("Time Stamp");

        current_user_db4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()){
                    final String date = child.getKey();

                    current_user_db4.child(date).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot child: dataSnapshot.getChildren()){
                                String time = child.getKey();
                                mLastDiaperTimeStamp.setText(date + " "+ time);

                                current_user_db4.child(date).child(time).child("last_diaper_status").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String last_diaper_status = dataSnapshot.getValue(String.class);
                                        mLastDiaperStatus.setText(last_diaper_status);
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });

                                current_user_db4.child(date).child(time).child("last_diaper_note").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String last_diaper_note = dataSnapshot.getValue(String.class);
                                        mLastDiaperNote.setText(last_diaper_note);
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });

                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        //end of Diaper Status













        //end of on create method
        return view;
    }

}
