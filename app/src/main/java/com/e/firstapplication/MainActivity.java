package com.e.firstapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
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

    EditText CheckIn, CheckOut, Room, Adult, Children;
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
        Adult = findViewById(R.id.Adult);
        Children = findViewById(R.id.Children);
        Room = findViewById(R.id.Room);
        Result = findViewById(R.id.Result);
        Calculate = findViewById(R.id.Calculate);

        ///Spinner Adapter
        String roomType[] = {"Deluxe", "Presidental", "Premium"};
        ArrayAdapter<String> SpinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, roomType);
        //setting spinner adapter
        RoomType.setAdapter(SpinnerAdapter);


        //Clickevent
        CheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCheckInDatePicker();
                Result.setText("");
            }
        });

        CheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCheckOutDatePicker();
                Result.setText("");

            }
        });
        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    //Validation
                    if (TextUtils.isEmpty((CheckIn.getText()))) {
                        CheckIn.setError("Please Enter the date");
                        return;
                    }
                    if (TextUtils.isEmpty((CheckOut.getText()))) {
                        CheckOut.setError("Please Enter the date");
                        return;
                    }
                    if (TextUtils.isEmpty((Room.getText()))) {
                        Room.setError("Please Enter the number");
                        return;
                    }
                    if (TextUtils.isEmpty((Adult.getText()))) {
                        Adult.setError("Please Enter the number");
                        return;
                    }
                    if (TextUtils.isEmpty((Children.getText()))) {
                        Children.setError("Please Enter the number");
                        return;
                    }

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

                    Date checkin = simpleDateFormat.parse(CheckIn.getText().toString());
                    Date checkout = simpleDateFormat.parse(CheckOut.getText().toString());

                    if (checkout.before(checkin)) {
                        CheckOut.setError("Invalid Checkout Date");
                        return;
                    }
                    int Days = (int) ((checkout.getTime() - checkin.getTime()) / (1000 * 60 * 60 * 24));
                    String room_type = RoomType.getSelectedItem().toString();
                    Integer NoOfRoom = Integer.parseInt(Room.getText().toString());
                    // Result.setText(Days+"");
                    int Charge;
                    int Total;
                    int GrandTotal;
                    switch (room_type) {
                        case "Deluxe":
                            Charge = 1200;
                             Total = Charge*NoOfRoom*Days;
                             GrandTotal=Total+(Total/100*13);
                             Result.append("Total :"+Total+"\n");
                             Result.append("VAT: 13% \n");
                             Result.append("Grand Total: "+GrandTotal+"\n");
                            break;
                        case "Presidental":
                            Charge= 1500;
                            Total = Charge*NoOfRoom*Days;
                            GrandTotal=Total+(Total/100*13);
                            Result.append("Total : Rs"+Total+"\n");
                            Result.append("VAT: 13% \n");
                            Result.append("Grand Total: Rs"+GrandTotal+"\n");
                            break;
                        case "Premium":
                            Charge=1000;
                            Total = Charge*NoOfRoom*Days;
                            GrandTotal=Total+(Total/100*13);
                            Result.append("Total : Rs"+Total+"\n");
                            Result.append("VAT: 13% \n");
                            Result.append("Grand Total: Rs"+GrandTotal+"\n");
                            break;
                    }


                } catch (ParseException e) {
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
                String date = year + "/" + (month+1) + "/" + dayOfMonth;
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
                String date = year + "/" + (month+1) + "/" + dayOfMonth;

                CheckOut.setText(date);
            }
        },
                year, month, day);
        datePickerDialog.show();
    }


}


