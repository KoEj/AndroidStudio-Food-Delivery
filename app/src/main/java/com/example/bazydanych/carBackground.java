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
import java.net.URLDecoder;
import java.net.URLEncoder;

@SuppressLint("StaticFieldLeak")
public class carBackground  extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;
    Intent intent_car;

    String[] splitted;
    String ID;

    public carBackground(Context con) {
        context = con;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Test");
    }

    @Override
    protected void onPostExecute(String s) {
        //alertDialog.setMessage(s);
        //alertDialog.show();

        if(splitted[0].equals("ConnectedOkOk")) {
            context.startActivity(intent_car);
        } else {
            Toast.makeText(context, "Brak danych w bazie danych!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        String connection = "http://192.168.0.15/car.php";
        ID = strings[0];
        String result = "";
        String line = "";

        try {
            URL url = new URL(connection);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(ID, "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();


            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-1"));

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            splitted = result.split("#");

            if(splitted[0].equals("ConnectedOkOk")) {
                intent_car = new Intent(context, carActivity.class);
                intent_car.putExtra("connection", splitted[0]);
                intent_car.putExtra("calendar", splitted[1]);
                intent_car.putExtra("car", splitted[2]);
                return result;
            }
            return "Błąd podłączenia z bazą danych";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
