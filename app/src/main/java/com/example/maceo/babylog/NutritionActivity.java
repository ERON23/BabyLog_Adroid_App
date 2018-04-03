package com.example.maceo.babylog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NutritionActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {

    Button b_start;
    TextView start_result;
    Button save_button;

    private Spinner spinner1,spinner2;
//    private Button btnSubmit;

    private int dayFinal, monthFinal, yearFinal;
    // DatePickerDialog.OnDateSetListener from_dateListener,to_dateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        start_result =(TextView) findViewById(R.id.start_result);
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

        yearFinal = year;
        monthFinal = month + 1;
        dayFinal = day;

        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog =new TimePickerDialog(NutritionActivity.this, NutritionActivity.this,
                hour,minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();

    }



    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        String amOrPm = " AM";
        if(hour > 12){
            hour = hour - 12;
            amOrPm = " PM";
        }
        start_result.setText(monthFinal + "/"+ dayFinal + "/"+ yearFinal + " "+ hour + ":"+ minute + amOrPm);
    }

    // add items into spinner dynamically
    public void addItemsOnSpinner2() {

        spinner2 = (Spinner) findViewById(R.id.right_breast_feed_spinner);
        List<String> list = new ArrayList<String>();
        list.add(" Choose");
        list.add("Vitamin");
        list.add("Liquid");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.left_breast_feed_spinner);
//        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
