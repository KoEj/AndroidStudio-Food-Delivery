package com.example.bazydanych;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
    Button button_lokalizacja;
    TextView order_local_text;
    TextView order_delivery_text;
    TextView order_payment_text;
    TextView order_status;

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
        String[] adres ={""};
        String[] klient ={""};
        String[] lokal={""};
        String[] order_number;
        final String[] buttonStatus = {"0"};

        Intent intent = getIntent();
        fa = this;

        button_accepted = findViewById(R.id.button_accepted);
        button_denied = findViewById(R.id.button_denied);
        button_help = findViewById(R.id.button_help);
        button_lokalizacja = findViewById(R.id.button_lokalizacja);
        order_local_text = findViewById(R.id.order_local_text);
        order_delivery_text = findViewById(R.id.order_delivery_text);
        order_payment_text = findViewById(R.id.order_payment_text);
        order_status = findViewById(R.id.order_status);

        String ID = getIntent().getStringExtra("ID");

        set_status[0] = intent.getStringExtra("set_status");
        set_klient[0] = intent.getStringExtra("set_klient");
        set_adres[0] = intent.getStringExtra("set_adres");
        set_lokal[0] = intent.getStringExtra("set_lokal");
        set_platnosc[0] = intent.getStringExtra("set_platnosc");
        order_number = intent.getStringExtra("order_id").split("\n");

        adres = intent.getStringExtra("set_adres").split(" ");
        klient= intent.getStringExtra("set_klient").split(" ");
        lokal = intent.getStringExtra("set_lokal").split(" ");


        order_local_text.setText(lokal[1] + " "+ lokal[2] + adres[6]);
        if(adres[4].equals("")) {
            order_delivery_text.setText(klient[6] +" "+ klient[7] + klient[5] + "\n" + klient[2] + klient[3] + klient[4]
                    + "\n" + adres[2] +" "+ adres[3] +"\n"+ adres[6] +" "+ adres[7]);
        } else if (adres[5].equals("")) {
            order_delivery_text.setText(klient[6] + klient[7] + "\n" + klient[5] + "\n" + klient[2] + klient[3] + klient[4]
                    + "\n" + adres[2] +" "+ adres[3] +"/" + adres[4] +"\n"+ adres[6] +" "+ adres[7]);
        } else {
            order_delivery_text.setText(klient[6] +" "+klient[7] + klient[5] + "\n" + klient[2] + klient[3] + klient[4]
                    + "\n" + adres[2] +" "+ adres[3] +"/" + adres[4] +" lokal:" + adres[5] +"\n"+ adres[6] +" "+ adres[7]);
        }
        order_payment_text.setText(set_platnosc[0]);
        order_status.setText(set_status[0]);

        button_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_order_help = new Intent(orderActivity.this,orderHelpActivity.class);
                startActivity(intent_order_help);
            }
        });

        button_accepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonStatus[0] = "1";
                orderButtonBackground bg = new orderButtonBackground(orderActivity.this);
                bg.execute(order_number[0],buttonStatus[0]);
                fa.finish();
            }
        });

        String[] finalOrder_number1 = order_number;
        button_denied.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonStatus[0] = "0";
                orderButtonBackground bg = new orderButtonBackground(orderActivity.this);
                bg.execute(order_number[0],buttonStatus[0]);
                fa.finish();
            }
        }));

        button_lokalizacja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String thePlace = (.getText().toString());
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=(" + thePlace + ")"));
                startActivity(intent);*/
            }
        });
    }
}