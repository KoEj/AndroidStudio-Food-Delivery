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

public class historyOrdersBackground  extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;
    Intent intent_history_orders;
    String set_status = "", set_klient = "", set_adres = "", set_lokal = "", set_platnosc = "";
    String[] splitted;
    String re_splitted;
    String[] new_splitted;
    String ID;
    
    public historyOrdersBackground(Context con) {
        context = con;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Test");
    }

    @Override
    protected void onPostExecute(String s) {
        alertDialog.setMessage(s);
        alertDialog.show();

        if (splitted[1].equals("Wyszukano") && splitted[0].equals("Connected")) {
            int n = Integer.parseInt(splitted[2]);
            String[] order = new String[n];
            String[] description = new String[n];



            for(int i=0;i<n;i++) {
                re_splitted = splitted[i+3];
                new_splitted = re_splitted.split("%");
                order[i] = new_splitted[0];
                description[i] = new_splitted[1];
            }

            Intent intent_history_orders = new Intent(context, historyOrdersActivity.class);
            intent_history_orders.putExtra("ID", ID);
            intent_history_orders.putExtra("order",order);
            intent_history_orders.putExtra("description",description);
            context.startActivity(intent_history_orders);
        }

    }

    @Override
    protected String doInBackground(String... strings) {
        String connection = "http://192.168.1.42/history.php";
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
            if (splitted[1].equals("Wyszukano") && splitted[0].equals("Connected")) {
                intent_history_orders = new Intent(context, historyOrdersActivity.class);
//                String[] order = {"1","2","3"};
//                String[] description = {"1","2","3"};
                return result;
            }
            return "Bład połączenia z bazą danych!";

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
