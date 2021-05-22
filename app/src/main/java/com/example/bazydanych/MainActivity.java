package com.example.bazydanych;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;


public class MainActivity extends AppCompatActivity {

    public static Activity fa;
    Button button;
    TextView editText;
    TextView login;
    TextView password;

    String ConnectionResult="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.textView3);
        login = findViewById(R.id.Login_name);
        password = findViewById(R.id.Password);
        fa = this;

        //ip = "192.168.0.15";
        //db = "mydb";

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = login.getText().toString();
                String pass = password.getText().toString();

                if(!user.equals("") && !pass.equals("")) {
                    loginBackground bg = new loginBackground(MainActivity.this);
                    bg.execute(user, pass);
                    //if (bg.getStatus() == AsyncTask.Status.FINISHED) {
                    //    MainActivity.this.finish();
                    //}
                }
                //Jezeli nie wyswietl komunikat ze bledne haslo i login
            }
        });
    }

}