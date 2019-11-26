package com.e.firstapplication;

import androidx.appcompat.app.AppCompatActivity;



import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    EditText CheckIn, CheckOut, Room;
    TextView Result;
    Spinner RoomType;
    Button Calculate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckIn = findViewById(R.id.CheckIn);
        CheckOut = findViewById(R.id.CheckOut);
        RoomType = findViewById(R.id.RoomType);
        Room = findViewById(R.id.Room);
        Result = findViewById(R.id.Result);
        Calculate = findViewById(R.id.Calculate);

        ///Spinner Adapter
        String roomType []= {"Deluxe","Presidental","Premium"};
        ArrayAdapter<String> SpinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, roomType);
        //setting spinner adapter
        RoomType.setAdapter(SpinnerAdapter);


        //Clickevent
        CheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCheckInDatePicker();
            }
        });

        CheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCheckOutDatePicker();
            }
        });
        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

                    Date checkin = simpleDateFormat.parse(CheckIn.getText().toString());
                    Date checkout =simpleDateFormat.parse(CheckOut.getText().toString());
                    int diffInDays = (int) ((checkout.getTime() - checkin.getTime()) / (1000 * 60 * 60 * 24));
                    Result.setText(diffInDays+"");}
                catch(ParseException e){
                    //handle expection
                }

            }
        });


    }




    //DatePicker
    private void loadCheckInDatePicker() {
        //To get current date
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        //To show datepicker
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date =  year + "/" + month + "/" + dayOfMonth;
                CheckIn.setText(date);
            }
        },
                year, month, day);
        datePickerDialog.show();
    }

    private void loadCheckOutDatePicker() {
        //To get current date
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);

        //To show datepicker
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date =  year + "/" + month + "/" + dayOfMonth;

                CheckOut.setText(date);
            }
        },
                year, month, day);
        datePickerDialog.show();
    }


}


