package com.example.maceo.babylog;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class Tab3 extends Fragment {

    //PieChart pieChart;
    LineChart mBottleFeedingChart;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseRef;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");


        // __________________START FOR CREATING BABY BOTTLE GRAPH_________________________________

        View view = inflater.inflate(R.layout.fragment_tab3, container, false);
        mBottleFeedingChart = (LineChart) view.findViewById(R.id.line_chart_bottle_feeding);

        // __________________FOR Retrieving database information_________________________________

        final DatabaseReference current_user_name2 = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(mAuth.getCurrentUser().getUid())
                .child("Feeding").child("Bottle Feeding").child("Time Stamp");


        current_user_name2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()){
                    final String date = child.getKey();
                    //String newDate = date;





                                //Calendar cal = Calendar.getInstance();

                                //String date = dataSnapshot.getValue(String.class);
                                String xValues [] = {"s"};

                                int currentSize = xValues.length;
                                int newSize = currentSize+1;
                                String[] tempArray = new String[newSize];
                                for (int i=0; i<currentSize; i++){
                                    tempArray[i] = xValues[i];
                                }
                                tempArray[newSize-1] = date;
                                xValues = tempArray;




                                float yValues [] = {50};
                                //String xValues [] = {"Frist","Second","Third", "fourth","fifth"};

                                XAxis xAxis = mBottleFeedingChart.getXAxis();
                                xAxis.setGranularity(1f);
                                xAxis.setGranularityEnabled(true);
                                drawLineGraph(yValues,xValues);


                                /*String time = child.getKey();
                                mLastDiaperTimeStamp.setText(date + " " + time);
                                String last_diaper_status = child.child("last_diaper_status").getValue(String.class);
                                mLastDiaperStatus.setText(last_diaper_status);
                                String last_diaper_note = child.child("last_diaper_note").getValue(String.class);
                                mLastDiaperNote.setText(last_diaper_note);*/






                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });






        /*current_user_name2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                //Calendar cal = Calendar.getInstance();

                String date = dataSnapshot.getValue(String.class);
                String xValues [] = {" "};

                int currentSize = xValues.length;
                int newSize = currentSize+1;
                String[] tempArray = new String[newSize];
                for (int i=0; i<currentSize; i++){
                    tempArray[i] = xValues[i];
                }
                tempArray[newSize-1] = date;
                xValues = tempArray;




                float yValues [] = {10,20,30,40,50};
                //String xValues [] = {"First","Second","Third", "fourth","fifth"};

                XAxis xAxis = mBottleFeedingChart.getXAxis();
                xAxis.setGranularity(1f);
                xAxis.setGranularityEnabled(true);
                drawLineGraph(yValues,xValues);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Toast.makeText(HomeActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/
        // __________________FOR Retrieving database information_________________________________



        /*float yValues [] = {10,20,30,40,50};
        String xValues [] = {"Frist","Second","Third", "fourth","fifth"};

        XAxis xAxis = mBottleFeedingChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        drawLineGraph(yValues,xValues);*/

        // __________________ENDING FOR CREATING BABY BOTTLE GRAPH_________________________________

        /*setData(10,20);
        mBottleFeedingChart.animateX(1000);*/






        /*// Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab3, container, false);

        pieChart = (PieChart) view.findViewById(R.id.pieChart);
        pieChart.setTouchEnabled(false);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);


        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(34f, "Bottle"));
        yValues.add(new PieEntry(23f, "Left Breast"));
        yValues.add(new PieEntry(40f, "Right Breast"));
        yValues.add(new PieEntry(15f, "Gerber"));

        *//*Description description = new Description();
        description.setText("Percentages of what baby has eaten.");
        description.setTextSize(15);
        pieChart.setDescription(description);*//*


//        description.setGravity(Gravity.CENTER);

        PieDataSet dataSet = new PieDataSet(yValues, "Baby's Food");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
*/



        return view;
    }



    // __________________FOR CREATING BABY BOTTLE GRAPH_________________________________

    private void drawLineGraph(float [] yValues, String [] xValues){
        ArrayList<Entry> yData = new ArrayList<>();
        for (int i = 0; i < yValues.length; i++){
            yData.add(new Entry(i,yValues[i]));
        }

        LineDataSet set1;
        set1 = new LineDataSet(yData,"Bottle Feeding");
        set1.setColor(Color.RED);
        LineData data = new LineData(set1);
        mBottleFeedingChart.getXAxis().setValueFormatter(new LabelFormatter(xValues));

        mBottleFeedingChart.setData(data);

    }


    public class LabelFormatter implements IAxisValueFormatter {
        private final String[ ] mLabels;

        public LabelFormatter(String[] labels) {
            mLabels = labels;;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mLabels[(int) value];
        }
    }

    // __________________FOR CREATING BABY BOTTLE GRAPH_________________________________



    /*private void setData(int xrange, int yrange){
        ArrayList<Entry> yVals1 = new ArrayList<>();


        yVals1.add(new Entry(2,3));
        yVals1.add(new Entry(1,2));
        yVals1.add(new Entry(5,7));
        yVals1.add(new Entry(6,4));




        *//*for (int i = 0; i<count; i++){

            float val = (float) (Math.random()*range)+50;
            yVals1.add(new Entry(i,val));

        }*//*


        LineDataSet set1;
        set1 = new LineDataSet(yVals1,"Bottle Feeding");
        set1.setColor(Color.RED);
        LineData data = new LineData(set1);
        mBottleFeedingChart.setData(data);


    }*/

}
