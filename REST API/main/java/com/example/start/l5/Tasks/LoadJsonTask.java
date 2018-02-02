package com.example.start.l5.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.start.l5.Interfaces.MyJsonResponseListener;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by start on 2017-11-15.
 */

public class LoadJsonTask extends AsyncTask<String, String, String> {

    private MyJsonResponseListener myListener;

    public LoadJsonTask(MyJsonResponseListener myListener) {
        this.myListener = myListener;
    }

    @Override
    protected void onPostExecute(String s) {
        myListener.onJsonResponseChange(s);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            StringBuilder response = new StringBuilder();

            URL u = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {

                BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()), 1024);
                String line;

                while ((line = input.readLine()) != null) {
                    response.append(line);
                }

                input.close();
            }

            return new JSONObject(response.toString()).getString("response");

        } catch (Exception e) {
            Log.e("TaskError", e.getMessage());
            return "Error";
        }

    }
}
