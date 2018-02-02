package com.example.start.l5.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.start.l5.Interfaces.MyJsonResponseListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by start on 2017-11-24.
 */

public class LoadDebtorTask extends AsyncTask<String, String, String> {

    private MyJsonResponseListener myJsonResponseListener;

    public LoadDebtorTask(MyJsonResponseListener myJsonResponseListener) {
        this.myJsonResponseListener = myJsonResponseListener;
    }


    @Override
    protected void onPostExecute(String s) {
        myJsonResponseListener.onJsonResponseChange(s);
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL u = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            StringBuilder stringBuilder = new StringBuilder();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()), 1024);
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                bufferedReader.close();

                Log.e("dsdsdas", stringBuilder.toString());
                return stringBuilder.toString();
            }

            return "Error";
        } catch (IOException e) {
            Log.e("Task_ERROR", e.getMessage());

            return "Error";
        }
    }


}
