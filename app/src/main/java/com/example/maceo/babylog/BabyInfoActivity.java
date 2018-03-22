package com.example.maceo.babylog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.maceo.babylog.Model.Baby;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class BabyInfoActivity extends AppCompatActivity {
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private EditText mBabyBirthDate;
    private EditText mBabyWeight;
    private EditText mBabyName;
    private Spinner mBabyGender;
    private ImageButton mImageButton;
    Button mSaveButton;
    private TextView mTextView;

    ImageView mImageView;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    //declare firebase database
    // App 63 - part 2
    FirebaseDatabase firebaseDatabaseInstance; //helps gets firebase intance
    DatabaseReference databaseReference; // helps us find the reference

    String uniqueBabyID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_info);



        mImageButton = (ImageButton) findViewById(R.id.cal_button);
        mBabyName = (EditText) findViewById(R.id.baby_name);
        mBabyBirthDate = (EditText)findViewById(R.id.baby_birthday);
        mBabyWeight = (EditText) findViewById(R.id.baby_weight);
        mSaveButton = (Button) findViewById(R.id.savebaby_info_button);
        mTextView = (TextView)findViewById(R.id.textView5);
        mBabyGender = (Spinner) findViewById(R.id.baby_gender);
        String babyInfo = "Enter your baby's information";

        mImageView = (ImageView)findViewById(R.id.add_pic);

        mTextView.setText(babyInfo);



        firebaseDatabaseInstance = FirebaseDatabase.getInstance();
        //this line below creates a category in the database
        databaseReference = firebaseDatabaseInstance.getReference("Baby");
        //this line below will create a child with name key and a value of 123
        //databaseReference.child("key").setValue("123");



        mImageButton.setOnClickListener(new View.OnClickListener() {
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
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                mBabyBirthDate.setText(date);
            }
        };


        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // here we are declaring what the user types into the edit text
                String babyName = mBabyName.getText().toString();
                String babyBirthdate = mBabyBirthDate.getText().toString();
                String babyWeight = mBabyWeight.getText().toString();
                String babyGender = mBabyGender.getSelectedItem().toString();

                int babyWeightIntegerValue = 0;

                //spinner value and grabs the string selected from the Gender option
                mBabyGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                         String babyGender = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                Intent r = new Intent(BabyInfoActivity.this,HomeActivity.class);
                startActivity(r);


                // we conver the string weight value into an integer for baby weight
                try {
                    babyWeightIntegerValue = Integer.parseInt(babyWeight);
                } catch (Exception e) {

                    Log.i("TAG", e.getMessage());
                }


                // here we pass the constructor from baby.java
                produceANewBabyIntoDatabase(babyName,babyBirthdate,
                        babyWeightIntegerValue, babyGender);

            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    // function to make a new baby Category
    private void produceANewBabyIntoDatabase(String babyName, String babyBirthDate,
                                             int babyWeight, String babyGender) {
        if(TextUtils.isEmpty(uniqueBabyID)){
            uniqueBabyID = databaseReference.push().getKey();
        }
        // here we are creating a new baby object
        Baby baby = new Baby(babyName, babyBirthDate , babyWeight, babyGender);
        // here we are creating a category called "Baby"
        databaseReference.child(uniqueBabyID).setValue(baby);
    }


    private void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            mImageView.setImageURI(imageUri);
        }
    }
}
