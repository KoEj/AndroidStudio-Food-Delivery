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

    CalendarView calendarView;
    TextView shift_text;

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
                AsyncTask<String, String, String> x = bg_shift.execute(ID, date);

                shift_text.setText(x.toString());
                //System.out.print(x.toString());

                if (bg_shift.getStatus() == AsyncTask.Status.FINISHED) {
                    //shift_text.setText(bg_shift.toString());
                    //shiftActivity.super.onRestart();

                }
            }
        });
    }
}