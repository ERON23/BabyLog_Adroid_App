package com.example.maceo.babylog;
import android.content.Intent;
import android.view.View;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

import com.example.maceo.babylog.HomeActivity;
import com.example.maceo.babylog.R;

public class BabyInfoActivity extends AppCompatActivity {
    //DatePickerDialog.OnDateSetListener mDateSetListener;
    Button mSaveButton;
    Button attempt;
    //static final String TAG = "MainActivity";

    //TextView mDisplayDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_info);

        //mDisplayDate = (TextView) findViewById(R.id.baby_birthday);
        mSaveButton = (Button) findViewById(R.id.savebaby_info_button);


        /*mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        BabyInfoActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });*/

        /*mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };*/


        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent r = new Intent(BabyInfoActivity.this,HomeActivity.class);
                startActivity(r);

            }
        });
    }
}
