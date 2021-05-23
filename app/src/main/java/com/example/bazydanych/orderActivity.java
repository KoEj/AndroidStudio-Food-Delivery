package com.example.bazydanych;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class orderActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static Activity fa;

    Button button_help;
    Button button_accepted;
    Button button_denied;
    Button button_refresh;
    TextView order_local_text;
    TextView order_delivery_text;
    TextView order_payment_text;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        final String[] set_status = {""};
        final String[] set_klient = { "" };
        final String[] set_adres = { "" };
        final String[] set_lokal = { "" };
        final String[] set_platnosc = { "" };
        Intent intent = getIntent();
        fa = this;

        button_refresh = findViewById(R.id.button_refresh);
        button_accepted = findViewById(R.id.button_accepted);
        button_denied = findViewById(R.id.button_denied);
        button_help = findViewById(R.id.button_help);
        order_local_text = findViewById(R.id.order_local_text);
        order_delivery_text = findViewById(R.id.order_delivery_text);
        order_payment_text = findViewById(R.id.order_payment_text);

        String ID = getIntent().getStringExtra("ID");

        set_status[0] = intent.getStringExtra("set_status");
        set_klient[0] = intent.getStringExtra("set_klient");
        set_adres[0] = intent.getStringExtra("set_adres");
        set_lokal[0] = intent.getStringExtra("set_lokal");
        set_platnosc[0] = intent.getStringExtra("set_platnosc");

        order_local_text.setText(set_lokal[0]);
        order_delivery_text.setText(set_klient[0] + "\n" + set_adres[0]);
        order_payment_text.setText(set_platnosc[0] + "\n" + set_status[0]);

        button_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderBackground bg = new orderBackground(orderActivity.this);
                bg.execute(ID);

                if(bg.getStatus() == AsyncTask.Status.FINISHED) {
                    //finish();
                }


            }
        });

        button_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_order_help = new Intent(orderActivity.this,orderHelpActivity.class);
                startActivity(intent_order_help);
            }
        });
    }
}