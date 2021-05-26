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
public class loginBackground  extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;
    Intent intent_login;

    public loginBackground(Context con) {
        context = con;
    }


    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login status");
        intent_login = new Intent(context, loggedActivity.class);
    }

    @Override
    protected void onPostExecute(String s) {
        if(s.equals("Logowanie udane!")) {
            context.startActivity(intent_login);
            MainActivity.fa.finish();
            Toast.makeText(context,"Logowanie udane!",Toast.LENGTH_LONG).show();
        }
        else {
            alertDialog.setMessage(s);
            alertDialog.show();
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        String connection = "http://192.168.1.42/login.php";
        String username = strings[0];
        intent_login.putExtra("ID", username);
        String password = strings[1];
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
            String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+
                    "&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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

            if (result.equals("Connectedlogin success")) {
                return "Logowanie udane!";
            } else {
                return "Podałeś zły login, hasło lub użytkownik nie istnieje.";
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
