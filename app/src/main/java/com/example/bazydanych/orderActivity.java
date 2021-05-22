package com.example.bazydanych;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class orderActivity extends AppCompatActivity {

    Button button_help;
    Button button_accepted;
    Button button_denied;
    Button button_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        button_refresh = findViewById(R.id.button_refresh);
        button_accepted = findViewById(R.id.button_accepted);
        button_denied = findViewById(R.id.button_denied);
        button_help = findViewById(R.id.button_help);
        String ID = getIntent().getStringExtra("ID");

        forgotBackground bg = new forgotBackground(orderActivity.this);
        System.out.print("SIEMA");

        button_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_order_help = new Intent(orderActivity.this,orderHelpActivity.class);
                startActivity(intent_order_help);
            }
        });
    }
}