package com.example.bazydanych;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.*;


public class MainActivity extends AppCompatActivity {

    Button button;
    TextView editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.textView3);

        /*Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?"
                    + "user=root&password=root1234");

            editText.setBackgroundColor(Color.GREEN);
            editText.setText("Connected to the database");

            conn.close();

            editText.setBackgroundColor(Color.BLUE);
            editText.setText("Disconnected from database");

        } catch (Exception e) {
            e.printStackTrace();
            editText.setText("BLAD");
        }*/

        try {
            tryConnection();
            editText.setText("poszlo");
        } catch (Exception e) {
            e.printStackTrace();
            editText.setText("nie poszlo");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Jezeli login i haslo sie zgadzaja to wykonaj
                //Intent intent_login = new Intent(MainActivity.this, loggedActivity.class);
                //startActivity(intent_login);
                //MainActivity.this.finish();

                //Jezeli nie wyswietl komunikat ze bledne haslo i login
            }
        });
    }


    public boolean tryConnection() throws Exception {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?"
                    + "user=root&password=root1234");
            boolean isValid = connection.isValid(2);
            connection.close();

            return isValid;
    }

}