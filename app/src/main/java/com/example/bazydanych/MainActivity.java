package com.example.bazydanych;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static Activity fa;

    Button login_button;
    Button forgot_button;
    TextView login;
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login_button = findViewById(R.id.button);
        forgot_button = findViewById(R.id.Forgot_password);
        login = findViewById(R.id.Forgot_first);
        password = findViewById(R.id.Forgot_second);
        fa = this;

        //ip = "192.168.0.15";
        //db = "mydb";

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = login.getText().toString();
                String pass = password.getText().toString();

                if(!user.equals("") && !pass.equals("")) {
                    loginBackground bg = new loginBackground(MainActivity.this);
                    bg.execute(user, pass);
                }
            }
        });

        forgot_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_forgot = new Intent(MainActivity.this,forgotActivity.class);
                startActivity(intent_forgot);
            }
        });
    }

}