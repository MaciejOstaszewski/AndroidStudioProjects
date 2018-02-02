package com.example.start.l5.Tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


import com.example.start.l5.Interfaces.MyJsonResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by start on 2017-11-16.
 */

public class LoadLoginJsonTask extends AsyncTask<String, String, String> {

    private MyJsonResponseListener myListener;

    public LoadLoginJsonTask(MyJsonResponseListener myListener) {
        this.myListener = myListener;
    }

    @Override
    protected void onPostExecute(String s) {
        myListener.onJsonResponseChange(s);
    }

    @Override
    protected String doInBackground(String... params) {
        URL url;
        String response = "";
        try {
            url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            String parsedParams = getRequestData(params);
            writer.write(parsedParams);
            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }

            return response;
        } catch (Exception e) {
            e.printStackTrace();

        }


        return response;
    }


    private String getRequestData(String... params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean isUrl = true;
        for (String param : params) {
            if (isUrl) { //pomijamy pierwszy parametr w tablicy bo jest to nasz URL
                isUrl = false;
            } else {
                result.append("&");
                result.append(param);//dodajemy kolejno parametry
            }
        }
        return result.substring(1, result.length());
    }
}
