package com.example.maceo.babylog;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Calendar;

public class WeighyTrackingActivity extends AppCompatActivity {


    private ImageButton mImageButton;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private EditText mBabyBirthDate;


    Button savebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weighy_tracking);
        mImageButton = (ImageButton) findViewById(R.id.cal_button);
        mBabyBirthDate = (EditText)findViewById(R.id.baby_birthday);


        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        WeighyTrackingActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        NumberPicker noPicker;
        final TextView lb_w;
        lb_w = findViewById(R.id.lb);
        noPicker = findViewById(R.id.np);
        noPicker.setMaxValue(100);
        noPicker.setMinValue(0);
        noPicker.setWrapSelectorWheel(true);

        noPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                lb_w.setText(String.valueOf(newVal));
            }
        });




        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                mBabyBirthDate.setText(date);
            }
        };



        savebutton =(Button) findViewById(R.id.savebutton);

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i);
            }
        });

    }
}
