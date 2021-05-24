package com.example.bazydanych;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class carActivity extends AppCompatActivity {

    TextView car_text_car;
    TextView car_text_calendar;
    Button button_emergency;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        car_text_car = findViewById(R.id.car_text_car);
        car_text_calendar = findViewById(R.id.car_text_calendar);
        button_emergency = findViewById(R.id.button_emergency);

        Intent intent = getIntent();
        String calendar = intent.getStringExtra("calendar");
        String car = intent.getStringExtra("car");

        String[] car_splitted = car.split("%");
        String[] calendar_splitted = calendar.split("%");

        car_text_car.setText(car_splitted[0] + "\n" + car_splitted[1] +"\n"+ car_splitted[2] + "\n" + car_splitted[3] + "\n" + car_splitted[4]);
        car_text_calendar.setText(calendar_splitted[0] + "\n" + calendar_splitted[1] + "\n" + calendar_splitted[2]);

        button_emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_dial = new Intent(Intent.ACTION_DIAL);
                intent_dial.setData(Uri.parse("tel:+48444444444"));
                startActivity(intent_dial);
            }
        });
    }
}