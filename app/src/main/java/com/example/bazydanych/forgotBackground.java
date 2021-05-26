package com.example.bazydanych;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

@SuppressLint("StaticFieldLeak")
public class forgotBackground  extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;

    public forgotBackground(Context con) {
        context = con;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Password change");
    }

    @Override
    protected void onPostExecute(String s) {
        if(s.equals("Zmiana hasła udana! Zaloguj się nowym hasłem.")) {
            Intent intent_back_to_main = new Intent(context, MainActivity.class);
            context.startActivity(intent_back_to_main);
            forgotActivity.fa.finish();
            Toast.makeText(context,"Zmiana hasła udana! Zaloguj się nowym hasłem.",Toast.LENGTH_LONG).show();
        } else {
            alertDialog.setMessage(s);
            alertDialog.show();
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        String connection = "http://192.168.1.42/forgot.php";

        String first_name= strings[0];
        String second_name = strings[1];
        String ID= strings[2];
        String new_pswd = strings[3];
        String new_pswd_2 = strings[4];
        String result = "";
        String line = "";

        try {
            URL url = new URL(connection);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data = URLEncoder.encode("first_name","UTF-8")+"="+URLEncoder.encode(first_name,"UTF-8")+
                    "&"+URLEncoder.encode("second_name","UTF-8")+"="+URLEncoder.encode(second_name,"UTF-8") +
                    "&"+URLEncoder.encode("ID","UTF-8")+"="+URLEncoder.encode(ID,"UTF-8") +
                    "&"+URLEncoder.encode("new_pswd","UTF-8")+"="+URLEncoder.encode(new_pswd,"UTF-8") +
                    "&"+URLEncoder.encode("new_pswd_2","UTF-8")+"="+URLEncoder.encode(new_pswd_2,"UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"ISO-8859-1"));

            while((line = bufferedReader.readLine()) != null) {
                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            if (result.equals("Connectedpassword changed")) {
                return "Zmiana hasła udana! Zaloguj się nowym hasłem.";
            } else if (result.equals("Connectedpassword not changed")) {
                return "Wprowadzone hasła się nie zgadzają!";
            } else {
                return "Zostały podane złe dane! Sprawdź imię, nazwisko oraz ID. Pamiętaj: imię i nazwisko muszą zaczynać się od dużej litery!";
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
