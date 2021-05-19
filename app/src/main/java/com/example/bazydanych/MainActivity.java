package com.example.bazydanych;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Jezeli login i haslo sie zgadzaja to wykonaj
                Intent intent_login = new Intent(MainActivity.this, loggedActivity.class);
                startActivity(intent_login);
                MainActivity.this.finish();

                //Jezeli nie wyswietl komunikat ze bledne haslo i login
            }
        });
    }
}