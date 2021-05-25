package com.example.bazydanych;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

public class shiftActivity extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    public static Activity fa;

    @SuppressLint("StaticFieldLeak")
    public static CalendarView calendarView;
    @SuppressLint("StaticFieldLeak")
    public static TextView shift_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift);
        calendarView = findViewById(R.id.CalendarView);
        shift_text = findViewById(R.id.shift_text);
        String ID = getIntent().getStringExtra("ID");


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = year + "-" + month + "-" + dayOfMonth;
                shiftBackground bg_shift = new shiftBackground(shiftActivity.this);
                bg_shift.execute(ID, date);
            }
        });
    }
}