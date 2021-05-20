package com.example.bazydanych;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.*;


public class MainActivity extends AppCompatActivity {

    Button button;
    TextView editText;
    String ip,db,user,pswd;
    Connection con = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.textView3);

        //    /usr/local/etc
        //    my.cnf
        ip = "192.168.0.15";
        db = "mydb";
        user = "admin";
        //pswd = "root1234";
        pswd = "some_pass";

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckLogin checkLogin = new CheckLogin();
                checkLogin.execute("");
                //Jezeli login i haslo sie zgadzaja to wykonaj
                //Intent intent_login = new Intent(MainActivity.this, loggedActivity.class);
                //startActivity(intent_login);
                //MainActivity.this.finish();

                //Jezeli nie wyswietl komunikat ze bledne haslo i login
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    public class CheckLogin extends AsyncTask<String,String,String> {
        String z = "";

        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("connecting");
                //con = DriverManager.getConnection("jdbc:mysql://192.168.1.35:3306/mydb?user=admin&password=some_pass");
                con = connectionclass(user,pswd,db,ip);
                if (con == null) {
                    z = "Blad! Sprawdz lacze internetowe!";
                    editText.setText(z);
                }
                else {
                    editText.setText("dziala");
                    z="Działa ";
                    String query = "select * from ADRES";
                    Statement stat = con.createStatement();
                    ResultSet rs = stat.executeQuery(query);
                }
            } catch (Exception ex) {
                z= "error";
                ex.getMessage();
            }
            return z;
        }

        @Override
        protected void onPostExecute(String z) {
            Toast.makeText(MainActivity.this,z, Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("NewApi")
    public Connection connectionclass(String user, String pswd, String db, String ip) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            editText.setText("PROBUJE");
            ConnectionURL = "jdbc:mysql://" + ip + "/" + db + "?user=" + user + "&password=" + pswd;
            connection = DriverManager.getConnection("jdbc:mysql://192.168.0.15:3306/mydb?user=admin&password=some_pass");
                    //(ConnectionURL);
        } catch(Throwable e) {
            editText.setText("error ;cc");
            e.printStackTrace();
        }
        editText.setText("Done");
        return connection;
    }


}