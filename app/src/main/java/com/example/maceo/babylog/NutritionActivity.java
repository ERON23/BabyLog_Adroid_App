package com.example.maceo.babylog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NutritionActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {

    Button b_start;
    EditText start_result;
    Button save_button;

    private Spinner spinner1,spinner2;
//    private Button btnSubmit;

    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;
    // DatePickerDialog.OnDateSetListener from_dateListener,to_dateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        addItemsOnSpinner2();
//        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

        save_button =(Button) findViewById(R.id.save_Button);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),FeedingActivity.class);
                startActivity(i);
            }
        });

        b_start =(Button) findViewById(R.id.b_start);
        //b_finish =(Button) findViewById(R.id.b_finish);
        start_result =(EditText) findViewById(R.id.start_result);
        // finish_result =(EditText) findViewById(R.id.finish_result);

        b_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day =c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(NutritionActivity.this, NutritionActivity.this,
                        year,month,day);
                datePickerDialog.show();

            }
        });
    }

    @Override

    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        int yearFinal = year;
        int monthFinal = month + 1;
        int dayFinal = day;

        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog =new TimePickerDialog(NutritionActivity.this, NutritionActivity.this,
                hour,minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();

    }



    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        int hourFinal = hour;
        int minuteFinal = minute;

        start_result.setText(monthFinal + "/"+ dayFinal + "/"+ yearFinal + " "+ hourFinal + ":"+minuteFinal);


    }

    // add items into spinner dynamically
    public void addItemsOnSpinner2() {

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();
        list.add(" Choose more option");
        list.add("Vitamin");
        list.add("Liquid");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
    // get the selected dropdown list value
    /*public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
//        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        *//*btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NutritionActivity.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) +
                                "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
            }
        });*//*

    }*/
}
