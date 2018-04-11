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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class Tab3 extends Fragment {

    LineChart mBottleFeedingChart;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();

        View view = inflater.inflate(R.layout.fragment_tab3, container, false);

        // __________________START FOR CREATING BABY BOTTLE GRAPH_________________________________
        mBottleFeedingChart = (LineChart) view.findViewById(R.id.line_chart_bottle_feeding);

        // __________________START FOR Retrieving database(bottle time and amount in OZ) information_________________________________

        final DatabaseReference current_user_db4 = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(mAuth.getCurrentUser().getUid())
                .child("Feeding").child("Bottle Feeding").child("Time Stamp");

        current_user_db4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()){
                    final String date = child.getKey();

                    current_user_db4.child(date).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            String time1= "0";
                            String[] xValues={time1};
                            float amtInOZ1 = 0;
                            float[] yValues={amtInOZ1};

                            // for loop to iterate timestamps of all database records
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                String time2 = snapshot.getKey();

                                int currentSize = xValues.length;
                                int newSize = currentSize+1;
                                String[] tempArray = new String[newSize];
                                for (int i=0; i<currentSize; i++){
                                    tempArray[i] = xValues[i];
                                }
                                tempArray[newSize-1] = time2;
                                xValues = tempArray;

                            }

                            // for loop to iterate all amount in OZ recorded on database
                            for (DataSnapshot child: dataSnapshot.getChildren()){
                                String Amount_In_Oz2 = child.child("Amount_In_Oz").getValue(String.class);
                                float Amount_In_Oz2_f = Float.parseFloat(Amount_In_Oz2);

                                int currentSize = yValues.length;
                                int newSize = currentSize+1;
                                float[] tempArray = new float[newSize];
                                for (int i=0; i<currentSize; i++){
                                    tempArray[i] = yValues[i];
                                }
                                tempArray[newSize-1] = Amount_In_Oz2_f;
                                yValues = tempArray;

                            }

                            //here we can change graph features and settings
                            XAxis xAxis = mBottleFeedingChart.getXAxis();
                            xAxis.setGranularity(1f);
                            xAxis.setGranularityEnabled(true);
                            drawLineGraph(yValues,xValues);

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

        // __________________ENDING FOR Retrieving database(bottle time and amount in OZ) information_________________________________

        // __________________ENDING FOR CREATING BABY BOTTLE GRAPH_________________________________







        // end of on create method.
        return view;
    }

    // __________________START FOR CREATING BABY BOTTLE GRAPH_________________________________

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
        private final String[] mLabels;

        public LabelFormatter(String[] labels) {
            mLabels = labels;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mLabels[(int) value];
        }
    }

    // __________________ENDING FOR CREATING BABY BOTTLE GRAPH_________________________________

}
